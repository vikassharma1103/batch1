package com.capstore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Wishlist {
	
	@Id
	@Column(name="wishlistId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int wishlistId;
	
	@OneToOne(targetEntity=Customer.class)
	private Customer customer;
	
//	@Column(name="products")
	@OneToMany(targetEntity=Product.class,cascade=CascadeType.ALL)
	private List<Product> products = new ArrayList<>();
	
	public int getWishlistId() {
		return wishlistId;
	}
	public void setWishlistId(int wishlistId) {
		this.wishlistId = wishlistId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "Wishlist [wishlistId=" + wishlistId + ", customer=" + customer + ", products=" + products + "]";
	}
	public Wishlist(int wishlistId, Customer customer, List<Product> products) {
		super();
		this.wishlistId = wishlistId;
		this.customer = customer;
		this.products = products;
	}
	public Wishlist() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + wishlistId;
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
		Wishlist other = (Wishlist) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (wishlistId != other.wishlistId)
			return false;
		return true;
	}
	
}