package com.capstore.service;

import java.util.List;

import com.capstore.model.Coupons;
import com.capstore.model.Email;
import com.capstore.model.Product;
import com.capstore.model.Promos;

public interface ISendPromoService {

	public boolean sendEmailToUser(Email email);
	
	public boolean checkIfNewProductEmailsRequiredToBeSent();
	public Email getNewProductEmail(List<Product> products);
	public boolean markNewProductEmailsSent(List<Product> products);
	public boolean sendnewProductEmailsToAllCustomer();
	
	public Email getNewPromoEmail(Promos promo);
	public boolean sendPromoEmailsToAllCustomer(Promos promo);
	
	public Email getNewCouponEmail(Coupons coupon);
	public boolean sendCouponEmailsToAllCustomer(Coupons coupon);
}
