package com.shop.cart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.shop.cart.model.*;


public class CartControllerTest extends AbstractTest {
   @Override
   @Before
   public void setUp() {
      super.setUp();
   }
   
   @Test
   public void getProductsList() throws Exception {
      String uri = "/api/shop/products";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Product[] productlist = super.mapFromJson(content, Product[].class);
      assertTrue(productlist.length > 0);
      assertTrue(productlist.length == 5);
   }
   
   
   @Test
   public void getViewCart() throws Exception {
      String uri = "/api/shop/cart/1";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Cart cart = super.mapFromJson(content, Cart.class);
      assertTrue(cart !=null);
      assertTrue(cart.getCurrency().equalsIgnoreCase("EUR"));
   }
   
   /**
    * add 2 quantity of product 1 and check in the cart
    * /shop/{cartid}/{productid}/{quantity}
    * /shop/1/1/2
    */
   @Test
   public void addAndRemoveCartItem() throws Exception {
      String uri = "/api/shop/1/1/2";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Cart cart = super.mapFromJson(content, Cart.class);
      assertTrue(cart !=null);
      assertTrue(cart.getCurrency().equalsIgnoreCase("EUR"));
      assertTrue(cart.getCartItems().size() ==1 );
      for(CartItem cartItem : cart.getCartItems()) {
         assertTrue(cartItem.getSku().equalsIgnoreCase("1001") ); 
         assertTrue(cartItem.getQuantity() == 2);
      }
      
      
      // delete 2 quantity of product 1
      uri = "/api/shop/1/1/2";
      mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
    	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    	      
      status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      content = mvcResult.getResponse().getContentAsString();
      cart = super.mapFromJson(content, Cart.class);
      assertTrue(cart !=null);
      assertTrue(cart.getCurrency().equalsIgnoreCase("EUR"));
      assertTrue(cart.getCartItems().size() ==0 );
    
   }
   
} 