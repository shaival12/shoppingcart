package com.shop.cart.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity for Cart
 * @author shaival
 *
 */
@Entity
@Table(name = "cart")
public class Cart implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long idCart;
	private BigDecimal postAndTax;
	private BigDecimal subtotal;
	private BigDecimal total;
	private String currency;
	
	@OneToMany(mappedBy = "idCartItem")
	private List<CartItem> cartItems;

	public Cart() {
	}

	public Cart(BigDecimal subtotal, BigDecimal total, String currency, List<CartItem> cartItems) {
		super();
		this.subtotal = subtotal;
		this.total = total;
		this.currency = currency;
		this.cartItems = cartItems;
	}
	
	
	public Cart(String currency, List<CartItem> cartItems) {
		super();
		this.currency = currency;
		this.cartItems = cartItems;
	}

	
	public Long getIdCart() {
		return this.idCart;
	}

	public void setIdCart(Long idCart) {
		this.idCart = idCart;
	}
	

	public BigDecimal getTotal() {
		return total;
	}



	public void setTotal(BigDecimal total) {
		this.total = total;
	}



	public String getCurrency() {
		return currency;
	}



	public void setCurrency(String currency) {
		this.currency = currency;
	}

	
	@Column(name = "subtotal", nullable = false, precision = 10)
	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public List<CartItem> getCartItems() {
		return this.cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	public BigDecimal calculateTotal(){
		BigDecimal total = BigDecimal.ZERO;
		for (CartItem cartItem : this.getCartItems()) {
			total.add(cartItem.getSubTotal().multiply(new BigDecimal(cartItem.getQuantity())));		
		}
		return total;
	}
}
