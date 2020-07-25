package com.shop.cart.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.shop.cart.model.Product;
 
/**
 * Product Data access repository 
 * 
 * @author shaival
 *
 */
 
@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
	
	
}
