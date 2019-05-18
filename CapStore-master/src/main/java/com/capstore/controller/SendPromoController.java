package com.capstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstore.model.Coupons;
import com.capstore.model.Product;
import com.capstore.model.Promos;
import com.capstore.service.IProductService;
import com.capstore.service.ISendPromoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class SendPromoController {

	@Autowired
	private ISendPromoService sendPromoService;

	@Autowired
	private IProductService productService;

	@GetMapping("/sendemail")
	public ResponseEntity<List<Product>> getNewProductsList() {
		List<Product> productsWithoutEmailSent = productService.getProductsWithoutPromotionalEmailSent();
		return new ResponseEntity<List<Product>>(productsWithoutEmailSent, HttpStatus.OK);
	}

	@PostMapping("/sendemail/newproduct")
	public ResponseEntity<Boolean> sendNewProductEmails() {
		if (sendPromoService.checkIfNewProductEmailsRequiredToBeSent()) {
			if (sendPromoService.sendnewProductEmailsToAllCustomer()) {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);

	}

	@PostMapping("/sendemail/promo")
	public ResponseEntity<Boolean> sendNewPromoEmails(@RequestBody Promos promo) {
		if (sendPromoService.sendPromoEmailsToAllCustomer(promo)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);

	}
	@PostMapping("/sendemail/coupon")
	public ResponseEntity<Boolean> sendNewCouponEmails(@RequestBody Coupons coupon) {
		if (sendPromoService.sendCouponEmailsToAllCustomer(coupon)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);

	}
}
