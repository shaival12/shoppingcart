package com.shop.cart.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity for Product
 * @author shaival
 *
 */

@Entity
@Table(name = "product")
public class Product implements java.io.Serializable {

	// @Id annotation specifies the primary key of an entity.
    // @GeneratedValue provides the generation strategy specification for the primary key values.
    @Id
    @GeneratedValue
	private Long idProduct;
	private String sku;
	private String name;
	private String description;
	private String imgUrl;
	private BigDecimal price; //GBP need to convert to EUR in Cart.
	private boolean inStock;
	
	public Product() {
	}


	public Product(String sku, String name, String description, String imgUrl, BigDecimal price, 
			boolean inStock) {
		super();
		this.sku = sku;
		this.name = name;
		this.description = description;
		this.imgUrl = imgUrl;
		this.price = price;
		this.inStock = inStock;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idproduct", unique = true, nullable = false)
	public Long getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}


	@Column(name = "description", nullable = false, length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "price", nullable = false, precision = 10)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "sku", nullable = false, length = 25)
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "imgUrl", nullable = true, length = 100)
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Column(name = "in_stock", nullable = false, length = 100)
	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((idProduct == null) ? 0 : idProduct.hashCode());
		result = prime * result + ((imgUrl == null) ? 0 : imgUrl.hashCode());
		result = prime * result + (inStock ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
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
		Product other = (Product) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idProduct == null) {
			if (other.idProduct != null)
				return false;
		} else if (!idProduct.equals(other.idProduct))
			return false;
		if (imgUrl == null) {
			if (other.imgUrl != null)
				return false;
		} else if (!imgUrl.equals(other.imgUrl))
			return false;
		if (inStock != other.inStock)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", sku=" + sku + ", name=" + name + ", description=" + description
				+ ", imgUrl=" + imgUrl + ", price=" + price + ", inStock=" + inStock + "]";
	}
}
