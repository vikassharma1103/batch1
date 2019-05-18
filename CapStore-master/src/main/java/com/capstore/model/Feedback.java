package com.capstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Feedback {

	@Id
	@Column(name="feedbackId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int feedbackId;
	
//	@Column(name="customerId")
	@OneToOne(targetEntity=Customer.class)
	private Customer customer;
	
//	@Column(name="productId")
	@OneToOne(targetEntity=Product.class)
	private Product product;
	@Column(name="ratingProduct")
	private double ratingProduct;
	@Column(name="ratingMerchant")
	private double ratingMerchant;
	@Column(name="comment")
	private String comment;
	
//	@Column(name="merchantId")
	@OneToOne(targetEntity=Merchant.class)
	private Merchant merchant;
	
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getRatingProduct() {
		return ratingProduct;
	}
	public void setRatingProduct(double ratingProduct) {
		this.ratingProduct = ratingProduct;
	}
	public double getRatingMerchant() {
		return ratingMerchant;
	}
	public void setRatingMerchant(double ratingMerchant) {
		this.ratingMerchant = ratingMerchant;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", customer=" + customer + ", product=" + product
				+ ", ratingProduct=" + ratingProduct + ", ratingMerchant=" + ratingMerchant + ", comment=" + comment
				+ ", merchant=" + merchant + "]";
	}
	public Feedback(int feedbackId, Customer customer, Product product, double ratingProduct, double ratingMerchant,
			String comment, Merchant merchant) {
		super();
		this.feedbackId = feedbackId;
		this.customer = customer;
		this.product = product;
		this.ratingProduct = ratingProduct;
		this.ratingMerchant = ratingMerchant;
		this.comment = comment;
		this.merchant = merchant;
	}
	public Feedback() {
		super();
	}
}