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
public class Cart {
	
	@Id
	@Column(name="cartId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cartId;
	@OneToOne(targetEntity=Customer.class)
	private Customer customer;
	@OneToMany(targetEntity=CartProduct.class,cascade=CascadeType.ALL)
	private List<CartProduct> cartProducts = new ArrayList<>();
	private int minimumAmount;

	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<CartProduct> getCartProducts() {
		return cartProducts;
	}
	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
	public int getMinimumAmount() {
		return minimumAmount;
	}
	public void setMinimumAmount(int minimumAmount) {
		this.minimumAmount = minimumAmount;
	}
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", customer=" + customer + ", cartProducts=" + cartProducts
				+ ", minimumAmount=" + minimumAmount + "]";
	}
	public Cart(int cartId, Customer customer, List<CartProduct> cartProducts, int minimumAmount) {
		super();
		this.cartId = cartId;
		this.customer = customer;
		this.cartProducts = cartProducts;
		this.minimumAmount = minimumAmount;
	}
	public Cart() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartId;
		result = prime * result + ((cartProducts == null) ? 0 : cartProducts.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + minimumAmount;
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
		if (cartId != other.cartId)
			return false;
		if (cartProducts == null) {
			if (other.cartProducts != null)
				return false;
		} else if (!cartProducts.equals(other.cartProducts))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (minimumAmount != other.minimumAmount)
			return false;
		return true;
	}
	
}
