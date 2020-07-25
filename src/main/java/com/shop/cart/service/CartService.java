package com.shop.cart.service;

import java.util.Date;

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
	
	
	public void addCartItem(Long productid, Integer quantity) {
		//Todo
	}
	
	public void removeCartItem(Long productid, Integer quantity) {
		//Todo
	}

	
	/*
	 * public void add(Long idCart, Long idProduct, Integer quantity) { Cart cart =
	 * cartDao.findBy(idCart); Product product = productDao.findBy(idProduct);
	 * cart.getLinesItems().add(new LineItem(cart, product, quantity,
	 * product.getPrice())); cartDao.update(cart); }
	 */

	

}
