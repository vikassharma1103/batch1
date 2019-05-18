package com.capstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Login {

	@Id
	@Column(name="serialNo")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int serialNo;
	
	@Column(unique=true,name="emailId")
	private String emailId;
	private String password;
	private String userTypes;
	
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
	public String getUserTypes() {
		return userTypes;
	}
	public void setUserTypes(String userTypes) {
		this.userTypes = userTypes;
	}
	@Override
	public String toString() {
		return "Login [serialNo=" + serialNo + ", emailId=" + emailId + ", password=" + password + ", userTypes="
				+ userTypes + "]";
	}
	public Login(int serialNo, String emailId, String password, String userTypes) {
		super();
		this.serialNo = serialNo;
		this.emailId = emailId;
		this.password = password;
		this.userTypes = userTypes;
	}
	public Login() {
		super();
	}
}