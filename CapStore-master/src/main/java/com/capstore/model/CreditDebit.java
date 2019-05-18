package com.capstore.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CreditDebit {
	
	@Id
	@Column(name="cardNumber")
	private long cardNumber;
	@Column(name="cardHolderName")
	private String cardHolderName;
	@Column(name="expiryDate")
	private Date expiryDate;
	@Column(name="cvv")
	private int cvv;
	@Column(name="balance")
	private double balance;
	@Column(name="pinNumber")
	private int pinNumber;
	
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getPinNumber() {
		return pinNumber;
	}
	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}
	@Override
	public String toString() {
		return "Credit_Debit [cardNumber=" + cardNumber + ", cardHolderName=" + cardHolderName + ", expiryDate="
				+ expiryDate + ", cvv=" + cvv + ", balance=" + balance + ", pinNumber=" + pinNumber + "]";
	}
	public CreditDebit(long cardNumber, String cardHolderName, Date expiryDate, int cvv, double balance,
			int pinNumber) {
		super();
		this.cardNumber = cardNumber;
		this.cardHolderName = cardHolderName;
		this.expiryDate = expiryDate;
		this.cvv = cvv;
		this.balance = balance;
		this.pinNumber = pinNumber;
	}
	public CreditDebit() {
		super();
	}
}