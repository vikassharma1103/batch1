package com.capstore.service;

import com.capstore.model.Coupons;

public interface ICouponService {

	public Coupons checkIfCouponCodeIsValid(String couponCode);
	public boolean generateCoupon(Coupons coupon);

}