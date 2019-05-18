package com.capstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class YouMailLogin {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int serialNo;
	private String emailId;
	private String password;
	
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "YouMailLogin [serialNo=" + serialNo + ", emailId=" + emailId + ", password=" + password + "]";
	}
	public YouMailLogin(int serialNo, String emailId, String password) {
		super();
		this.serialNo = serialNo;
		this.emailId = emailId;
		this.password = password;
	}
	public YouMailLogin() {
		super();
	}
}