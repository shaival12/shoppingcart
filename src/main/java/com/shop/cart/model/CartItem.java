package com.shop.cart.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	

	public CartItem(String sku, Cart cart, Integer quantity, String currency, BigDecimal subTotal) {
		super();
		this.sku = sku;
		this.cart = cart;
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

	@JsonIgnore
	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cart == null) ? 0 : cart.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((idCartItem == null) ? 0 : idCartItem.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
		result = prime * result + ((subTotal == null) ? 0 : subTotal.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		if (cart == null) {
			if (other.cart != null)
				return false;
		} else if (!cart.equals(other.cart))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (idCartItem == null) {
			if (other.idCartItem != null)
				return false;
		} else if (!idCartItem.equals(other.idCartItem))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		if (subTotal == null) {
			if (other.subTotal != null)
				return false;
		} else if (!subTotal.equals(other.subTotal))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "CartItem [idCartItem=" + idCartItem + ", sku=" + sku + ", quantity=" + quantity + ", currency="
				+ currency + ", subTotal=" + subTotal + ", cart=" + cart + "]";
	}
	
	
	

}
