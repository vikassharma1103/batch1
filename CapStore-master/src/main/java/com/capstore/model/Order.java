package com.capstore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="productOrder")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {
	
	@Id
	@Column(name="orderId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int orderId;

	@OneToOne(targetEntity=Customer.class)
	private Customer customer;
	
	@OneToOne(targetEntity=Cart.class)
	private Cart cart;
	
	@OneToMany(targetEntity=Shipment.class,cascade=CascadeType.ALL)
	private List<Shipment> shipments = new ArrayList<>();
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date orderDate;
	
	@OneToOne(targetEntity=Coupons.class)
	private Coupons coupon;

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public List<Shipment> getShipments() {
		return shipments;
	}
	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Coupons getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupons coupon) {
		this.coupon = coupon;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customer=" + customer + ", cart=" + cart + ", shipments=" + shipments
				+ ", orderDate=" + orderDate + ", coupon=" + coupon + "]";
	}
	public Order(int orderId, Customer customer, Cart cart, List<Shipment> shipments, Date orderDate, Coupons coupon) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.cart = cart;
		this.shipments = shipments;
		this.orderDate = orderDate;
		this.coupon = coupon;
	}
	public Order() {
		super();
	}
}