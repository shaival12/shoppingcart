package com.shop.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.shop.cart.repo.*;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.shop.cart.model.*;
import java.util.List;

/**
 * SpringBoot Start Application
 * @author shaival
 *
 */
@SpringBootApplication
@EnableScheduling
public class CartApplication implements CommandLineRunner {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartRepository cartRepository;

	public static void main(String[] args) {
		SpringApplication.run(CartApplication.class, args);
	}
	
	/**
	 * Preload Product data in the database
	 */
	@Override
	public void run(String... args) throws Exception {
		
	  //data for products 
	  //TODO: move to json file 
	  Product p1 = new Product("1001","iPhone 7","Smart phone 7","http://imgur/img7.png",new BigDecimal("700") ,true);
	  Product p2 = new Product("1002","iPhone 8","Smart phone 8","http://imgur/img8.png",new BigDecimal("800") ,true);
	  Product p3 = new Product("1003","iPhone 9","Smart phone 9","http://imgur/img9.png",new BigDecimal("900") ,true);
	  Product p4 = new Product("1004","iPhone 10","Smart phone 10","http://imgur/img10.png",new BigDecimal("1000") ,true);
	  Product p5 = new Product("1005","iPhone 11","Smart phone 11","http://imgur/img11.png",new BigDecimal("1100") ,true);
	  
	 
	  productRepository.save(p1);
	  productRepository.save(p2);
	  productRepository.save(p3);
	  productRepository.save(p4);
	  productRepository.save(p5);
	  
	  
	  //Get the list of products from DB
	  List<Product> productList = (List<Product>) productRepository.findAll();
	  productList.forEach(e->  System.out.println(e.getName()));
	  
	  //add a default Cart
	  Cart cart = new Cart(new BigDecimal("0.0"),new BigDecimal("0.0"),"EUR", null);
	  cartRepository.save(cart);
	  //Test a cart
	  cartRepository.findAll().forEach(e->  System.out.println(e.getCurrency()));
	  
	}

}
