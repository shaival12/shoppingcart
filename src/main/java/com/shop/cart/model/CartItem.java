package com.shop.cart.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * Entity for cart Item
 * @author shaival
 *
 */

@Entity
@Table(name = "cart_item")
public class CartItem implements java.io.Serializable {

	private Long idCartItem;
	private String sku;
	private Integer quantity;
	private String currency;
	private BigDecimal subTotal;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCart")
	private Cart cart;

	public CartItem() {
	}
	

	public CartItem(String sku, Integer quantity, String currency, BigDecimal subTotal) {
		super();
		this.sku = sku;
		this.quantity = quantity;
		this.currency = currency;
		this.subTotal = subTotal;
	}



	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idCartItem", unique = true, nullable = false)
	public Long getIdCartItem() {
		return this.idCartItem;
	}

	public void setIdCartItem(Long idCartItem) {
		this.idCartItem = idCartItem;
	}
	
	@Column(name = "quantity", nullable = false)
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public BigDecimal getSubTotal() {
		return subTotal;
	}


	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	
	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	

}
