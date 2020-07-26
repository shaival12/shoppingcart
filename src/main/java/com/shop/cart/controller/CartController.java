package com.shop.cart.controller;


import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.cart.exception.ProductNotFoundException;
import com.shop.cart.model.Cart;
import com.shop.cart.model.Product;
import com.shop.cart.service.CartService;
import com.shop.cart.service.ProductService;
 

@RestController
@RequestMapping("/api")
public class CartController {

	@Autowired
	CartService cartService;
	
	@Autowired
	ProductService productService;
	
	/**
	 * Add cartItem to cart with productid and quantity(optional)
	 * @param productid
	 * @param quanitity
	 */
	@PostMapping("/shop/{cartid}/{productid}/{quantity}")
    public @ResponseBody ResponseEntity<Cart> addCartItem(@PathVariable Long cartid,
    		         @PathVariable Long productid, 
    		         @PathVariable(required = false) Integer quantity) {
		
		//validate quantity
		if(quantity<0) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		if(quantity==null) {
			quantity = 1; //default
		}
		
		Cart cart = null;
		if(cartService.findById(cartid).isPresent()) {
			 cart = cartService.findById(cartid).get();  // find existing cart
		}else {
			 cart =  cartService.instantiateEmptyCart(); //create a new Cart
		}
		
		// add Product to Cart
		Product product = productService.findById(productid).get(); 
		cart = cartService.addCartItem(quantity, cart, product);
		
		return new ResponseEntity<Cart> (cart, HttpStatus.OK);
    }	
	
	/**
	 * delete a cartItem to cart with productid and quantity(optional)
	 * @param productid
	 * @param quanitity
	 */
	@DeleteMapping("/shop/{cartid}/{productid}/{quantity}")
    public @ResponseBody ResponseEntity<Cart> removeCartItem(@PathVariable Long cartid,
	              @PathVariable Long productid, 
	              @PathVariable(required = false) Integer quantity) {
		
		//validate quantity
		if(quantity<0) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		if(quantity==null) {
			quantity = 1;
		}
		
		Cart cart = cartService.findById(cartid).get();
		if(cart != null) {
			Product product = productService.findById(productid).get();
			cart = cartService.removeCartItem(quantity, cart, product);
		}
		
		return new ResponseEntity<Cart> (cart, HttpStatus.OK);
    }
	
	/**
	 * get All items of cart
	 * @param idCart
	 * @param request
	 * @return
	 * @throws URISyntaxException
	 */
	@GetMapping("/shop/cart/{id}")
	public @ResponseBody ResponseEntity<Cart> viewCart(@PathVariable Long id) {
		
		Boolean isPresent = cartService.findById(id).isPresent();
		if(!isPresent) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
		Cart cart = cartService.findById(id).get();		
		return new ResponseEntity<Cart> (cart, HttpStatus.OK);
	}

	/**
	 * get All products of ecommerce shop
	 * @param idCart
	 * @param request
	 * @return
	 * @throws URISyntaxException
	 */
	@GetMapping("/shop/products")
	public @ResponseBody ResponseEntity<List<Product>> getProductList() throws ProductNotFoundException  {
		List<Product> products = productService.findAll();
		return new ResponseEntity<List<Product>> (products, HttpStatus.OK);
	}
	
	
}
