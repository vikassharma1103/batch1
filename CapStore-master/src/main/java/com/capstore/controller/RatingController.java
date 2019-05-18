package com.capstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstore.service.IFeedbackService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1")
public class RatingController {
	
	@Autowired
	IFeedbackService feedbackService;
	

	@GetMapping("/getProductRating/{productId}")
	public ResponseEntity<Double> calculateProductRating(@PathVariable("productId") Integer productId ){
	
		double productRating = feedbackService.calculateProductRating(productId);
		return new ResponseEntity<Double>(productRating,HttpStatus.OK);
	}
	
	@GetMapping("/getMerchantRating/{merchantId}")
	public ResponseEntity<Double> calculateMerchantRating(@PathVariable("merchantId") Integer merchantId ){
	
		double merchantRating = feedbackService.calculateMerchantRating(merchantId);
		return new ResponseEntity<Double>(merchantRating,HttpStatus.OK);
	}
}