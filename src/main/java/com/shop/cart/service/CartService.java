package com.shop.cart.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.cart.model.Cart;
import com.shop.cart.model.CartItem;
import com.shop.cart.model.Product;
import com.shop.cart.repo.CartItemRepository;
import com.shop.cart.repo.CartRepository;



@Service
@Transactional
public class CartService {

	@Autowired
	CartRepository cartDao;
	
	@Autowired
	CartItemRepository cartItemDao;
	
	private static final String EUR_CURRENCY = "EUR";
	
	/**
	 * create a new Empty Cart 
	 * @return
	 */
	public Cart instantiateEmptyCart() {
		Cart cart = new Cart(new BigDecimal("0.0"),new BigDecimal("0.0"),EUR_CURRENCY, new ArrayList());
		cartDao.save(cart);// save to DB
		return cart;
	}
	
	/**
	 *  add cart Item
	 * @param quantity
	 * @param cart
	 * @param product
	 * @return
	 */
	public Cart addCartItem(Integer quantity, Cart cart, Product product) {
		
		if(cart.getCartItems()== null) { // if cart Empty and adding first Product
			cart.setCartItems(new ArrayList<CartItem>());
			addNewProductToCart(quantity, cart, product);
			
		}else { //if cartItems already exists
		    CartItem existingCartItem = checkIfProductAlreadyExistsIntheCart(cart, product);
		    
		    //update the existing Quantity in the CartItem 
		    if(existingCartItem!=null) {
		    	//update quantity
		    	existingCartItem.setQuantity(existingCartItem.getQuantity()+quantity);
		    	existingCartItem.setSubTotal(calculateCartItemSubTotal(existingCartItem.getQuantity(), product));
		    }else {
		    	addNewProductToCart(quantity, cart, product);
		    }
		}
		
		//do calculation of Total,SubTotal,PostAndTax
		cart.reCalculateCart();
		save(cart);
		
		return cart;
	}


	private CartItem checkIfProductAlreadyExistsIntheCart(Cart cart, Product product) {
		if(cart.getCartItems().size()==0) {
			return null;
		}
		CartItem existingCartItem = cart.getCartItems().stream()
				            .filter(e -> product.getSku().equals( e.getSku()))
				            .findFirst()
				            .orElse(null);
		return existingCartItem;
	}


	private void addNewProductToCart(Integer quantity, Cart cart, Product product) {
		
		CartItem entity = new CartItem(product.getSku(),cart,
				quantity,
				EUR_CURRENCY,
				calculateCartItemSubTotal(quantity, product));
		// save to db
		cartItemDao.save(entity);
		cart.getCartItems().add(entity);
	}


	private BigDecimal calculateCartItemSubTotal(Integer quantity, Product product) {
		BigDecimal subtotal = BigDecimal.ZERO;
		return subtotal.add(product.getPrice().multiply(new BigDecimal(quantity)));
	}
	
	
	/**
	 * remove Cart Item 
	 * @param quantity
	 * @param cart
	 * @param product
	 * @return
	 */
	public Cart removeCartItem(Integer quantity, Cart cart, Product product) {
		if(cart.getCartItems() != null) 
	    { //if cartItems already exists
		   
		    CartItem existingCartItem = checkIfProductAlreadyExistsIntheCart(cart, product);
		    
		    //update the existing Quantity in the CartItem 
		    if(existingCartItem!=null && existingCartItem.getQuantity()>=quantity) {
		    	//update quantity
		    	 existingCartItem.setQuantity(existingCartItem.getQuantity()-quantity);
		    	 existingCartItem.setSubTotal(calculateCartItemSubTotal(existingCartItem.getQuantity(), product));
		    	 
		    }
		    
		    // if quantity 0, remove from list
		    if(existingCartItem.getQuantity()==0) {
	    		// remove from Cart
		    	cart.getCartItems().removeIf(e -> product.getSku().equals(e.getSku()));
	    	 }
		    
		    //do calculation of Total,SubTotal,PostAndTax for Cart
			cart.reCalculateCart();
		    save(cart);
		    
		}
		
		return cart;
		
  }
	
	/**
	 * find a cart by id
	 * @param id
	 * @return
	 */
	public Optional<Cart> findById(Long id) {
		return cartDao.findById(id);
	}
	
	/**
	 * save a new Cart
	 * @param cart
	 */
	public void save(Cart cart) { 
		
		if(cart!=null) {
			cartDao.save(cart);
		}
		
	  }
	

}
