package com.capstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BankAccount {
	
	@Id
	@Column(name="accountNumber")
	private long accountNumber;
	@Column(name="customerName")
	private String customerName;
	@Column(name="userName")
	private String userName;
	@Column(name="userPassword")
	private String userPassword;
	@Column(name="balance")
	private double balance;
	
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", customerName=" + customerName + ", userName="
				+ userName + ", userPassword=" + userPassword + ", balance=" + balance + "]";
	}
	public BankAccount(long accountNumber, String customerName, String userName, String userPassword,
			double balance) {
		super();
		this.accountNumber = accountNumber;
		this.customerName = customerName;
		this.userName = userName;
		this.userPassword = userPassword;
		this.balance = balance;
	}
	public BankAccount() {
		super();
	}
}