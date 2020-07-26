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


	public BigDecimal getPostAndTax() {
		return postAndTax;
	}

	public void setPostAndTax(BigDecimal postAndTax) {
		this.postAndTax = postAndTax;
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
		return subtotal;
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
	
	
	/**
	 *  Do all fresh Calcualtion when quantity changes or New CartItem added
	 */
	public void reCalculateCart() {
		calculateSubTotal();
		calculatePostAndTax();
		calculateTotal();
	}
	
	/**
	 * Sum of SubTotal + PostAndTax
	 * @return
	 */
	private BigDecimal calculateTotal(){
		 BigDecimal total = getSubtotal().add(getPostAndTax());
		 setTotal(total);
		return total;
	}
	
	/**
	 * Sum of subtotal of all CartItems 
	 * @return
	 */
	private BigDecimal calculateSubTotal(){
		
		BigDecimal subTotal = this.getCartItems()
				 .stream()
			     .map(CartItem::getSubTotal)
			     .reduce(BigDecimal.ZERO, BigDecimal::add);
		
		setSubtotal(subTotal);
		return subTotal;
	}
	
	/**
	 * 10% of SubTotal
	 * @return
	 */
	private BigDecimal calculatePostAndTax(){
		BigDecimal postAndTax = (getSubtotal()
				.multiply(new BigDecimal(10))
				.divide(new BigDecimal(100)));		
		setPostAndTax(postAndTax);
		return postAndTax;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartItems == null) ? 0 : cartItems.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((idCart == null) ? 0 : idCart.hashCode());
		result = prime * result + ((postAndTax == null) ? 0 : postAndTax.hashCode());
		result = prime * result + ((subtotal == null) ? 0 : subtotal.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
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
		Cart other = (Cart) obj;
		if (cartItems == null) {
			if (other.cartItems != null)
				return false;
		} else if (!cartItems.equals(other.cartItems))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (idCart == null) {
			if (other.idCart != null)
				return false;
		} else if (!idCart.equals(other.idCart))
			return false;
		if (postAndTax == null) {
			if (other.postAndTax != null)
				return false;
		} else if (!postAndTax.equals(other.postAndTax))
			return false;
		if (subtotal == null) {
			if (other.subtotal != null)
				return false;
		} else if (!subtotal.equals(other.subtotal))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cart [idCart=" + idCart + ", postAndTax=" + postAndTax + ", subtotal=" + subtotal + ", total=" + total
				+ ", currency=" + currency + ", cartItems=" + cartItems + "]";
	}
	
	
	
}
