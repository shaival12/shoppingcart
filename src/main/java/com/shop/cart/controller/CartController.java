package com.shop.cart.controller;


import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.cart.exception.ProductNotFoundException;
import com.shop.cart.model.Cart;
import com.shop.cart.model.Product;
import com.shop.cart.service.CartService;
import com.shop.cart.service.ProductService;

@RestController
public class CartController {

	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;
	
	@PostMapping("/shop/{productid}/{quantity}")
    public void addCartItem(@PathVariable String productid, @PathVariable Integer quanitity) {

		//cartService.addCartItem(productid,quanitity);
    }
	
	
	@DeleteMapping("/shop/{productid}/{quantity}")
    public void removeCartItem(@PathVariable String productid, @PathVariable Integer quanitity) {
		
      // cartService.removeCartItem(productid,quanitity);
    }
	
	/**
	 * get All items of cart
	 * @param idCart
	 * @param request
	 * @return
	 * @throws URISyntaxException
	 */
	@GetMapping("/shop/cart")
	public @ResponseBody ResponseEntity<Cart> viewCart() {
		
		Cart cart = null;
		//TODO
		//cart = cartService.viewCart();
		
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
