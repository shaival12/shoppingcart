package com.shop.cart.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.shop.cart.model.Cart;
 
/**
 * Cart Data access repository 
 * @author shaival
 *
 */
 
@Repository
public interface CartRepository extends CrudRepository<Cart, Long>{
 
}
