package com.capstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.dao.ICouponDao;
import com.capstore.model.Coupons;

@Service("couponService")
public class CouponService implements ICouponService{

	@Autowired
	private ICouponDao couponsDao;
	
	@Override
	public Coupons checkIfCouponCodeIsValid(String couponCode) {
		
		Coupons myCoupon=couponsDao.findByCouponCode(couponCode);
		if(myCoupon.equals(null)) {
			return null;
		}
		return myCoupon;
	}

	@Override
	public boolean generateCoupon(Coupons coupon) {
		
		couponsDao.save(coupon);

		return true;
	}
}