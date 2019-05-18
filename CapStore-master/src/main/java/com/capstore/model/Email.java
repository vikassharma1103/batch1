package com.capstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Email {
	
	@Id
	@Column(name="serialNo")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int serialNo;
	
	@Column(name="receiverEmailId")
	private String receiverEmailId;
	
	@Column(name="senderEmailId")
	private String senderEmailId;
	
	@Column(name="message",columnDefinition="TEXT")
	private String message;
	
	private String link;
	
	@Column(name="imageUrl")
	private String imageUrl;
	
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public String getReceiverEmailId() {
		return receiverEmailId;
	}
	public void setReceiverEmailId(String receiverEmailId) {
		this.receiverEmailId = receiverEmailId;
	}
	public String getSenderEmailId() {
		return senderEmailId;
	}
	public void setSenderEmailId(String senderEmailId) {
		this.senderEmailId = senderEmailId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public String toString() {
		return "Email [serialNo=" + serialNo + ", receiverEmailId=" + receiverEmailId + ", senderEmailId="
				+ senderEmailId + ", message=" + message + ", link=" + link + ", imageUrl=" + imageUrl + "]";
	}
	
	public Email(int serialNo, String receiverEmailId, String senderEmailId, String message, String link,
			String imageUrl) {
		super();
		this.serialNo = serialNo;
		this.receiverEmailId = receiverEmailId;
		this.senderEmailId = senderEmailId;
		this.message = message;
		this.link = link;
		this.imageUrl = imageUrl;
	}
	public Email() {
		super();
	}
}