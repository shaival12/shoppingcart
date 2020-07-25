package com.shop.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.cart.repo.ProductRepository;
import com.shop.cart.exception.ProductNotFoundException;
import com.shop.cart.model.Product;

/**
 * Product service for crud related to Product
 * @author shaival
 *
 */
@Service
@Transactional
public class ProductService {

	@Autowired
	ProductRepository productDao;


	public List<Product> findAll() throws ProductNotFoundException {
		List<Product> products = (List<Product>) productDao.findAll();
		if (products.isEmpty() || products == null)
			throw new ProductNotFoundException();
		else
			return products;
	}

}