package com.shop.cart.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.shop.cart.model.CartItem;
 
/**
 * CartItem Data access repository 
 * @author shaival
 *
 */
@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long>{
 
}
