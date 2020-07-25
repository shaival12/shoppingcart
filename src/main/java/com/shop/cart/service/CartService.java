package com.shop.cart.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.cart.repo.CartRepository;
import com.shop.cart.model.*;



@Service
@Transactional
public class CartService {

	@Autowired
	CartRepository cartDao;
	
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
		    cart.getCartItems().add(new CartItem());
		
		    CartItem existingCartItem = checkIfProductAlreadyExistsIntheCart(cart, product);
		    
		    //update the existing Quantity in the CartItem 
		    if(existingCartItem!=null) {
		    	//update quantity
		    	existingCartItem.setQuantity(existingCartItem.getQuantity()+quantity);
		    }else {
		    	addNewProductToCart(quantity, cart, product);
		    }
		}
		
		return cart;
	}


	private CartItem checkIfProductAlreadyExistsIntheCart(Cart cart, Product product) {
		CartItem existingCartItem = cart.getCartItems().stream()
				            .filter(e -> e.getSku().equals(product.getSku()))
				            .findFirst()
				            .orElse(null);
		return existingCartItem;
	}


	private void addNewProductToCart(Integer quantity, Cart cart, Product product) {
		
		cart.getCartItems().add(new CartItem(product.getSku(),
				quantity,
				EUR_CURRENCY,
				calculateSubTotal(quantity, product)));
	}


	private BigDecimal calculateSubTotal(Integer quantity, Product product) {
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
		    	
		    	 if(existingCartItem.getQuantity()==0) {
		    		// remove from Cart
		    		 existingCartItem = null;
		    	 }
		    	 
		    }
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
