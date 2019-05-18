package com.capstore.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Shipment {

	@Id
	@Column(name = "shipmentId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int shipmentId;

/*//	@Column(name="orderId")
	@OneToOne(targetEntity = Order.class)
	private Order order;*/

	// @Column(name="addressId")
	@OneToOne(targetEntity = Address.class)
	private Address address;

//	@Column(name="productId")
	@OneToOne(targetEntity = Product.class)
	private Product product;
	private String deliveryStatus;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date deliveryDate;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dispatchDate;

	public int getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getString() {
		return deliveryStatus;
	}

	public void setString(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	@Override
	public String toString() {
		return "Shipment [shipmentId=" + shipmentId + ", address=" + address + ", product=" + product
				+ ", deliveryStatus=" + deliveryStatus + ", deliveryDate=" + deliveryDate + ", dispatchDate="
				+ dispatchDate + "]";
	}

	public Shipment(int shipmentId, Address address, Product product, String deliveryStatus,
			Date deliveryDate, Date dispatchDate) {
		super();
		this.shipmentId = shipmentId;
		this.address = address;
		this.product = product;
		this.deliveryStatus = deliveryStatus;
		this.deliveryDate = deliveryDate;
		this.dispatchDate = dispatchDate;
	}

	public Shipment() {
		super();
	}
}