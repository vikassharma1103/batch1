package com.capstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstore.model.Feedback;
import com.capstore.model.Merchant;
import com.capstore.service.ICustomerService;
import com.capstore.service.IFeedbackService;
import com.capstore.service.IMerchantService;
import com.capstore.service.IProductService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1")
public class FeedbackController {
	
	@Autowired
	IFeedbackService feedbackService;
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	IProductService productService;
	
	@Autowired
	IMerchantService merchantService;
	
	@PostMapping("/feedback/{customerId}/{merchantId}/{productId}")
	public ResponseEntity<Boolean> submitFeedback(
			@PathVariable("customerId")int customerId, @PathVariable("merchantId") int merchantId,
			@PathVariable("productId") int productId){
		Feedback feedback = new Feedback();
		   feedback.setCustomer(customerService.getCustomerByCustomerId(customerId));
		   feedback.setMerchant(merchantService.getMerchantByMerchantId(merchantId));
		   feedback.setProduct(productService.getProduct(productId));
		
		feedbackService.submitFeedback(feedback);
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);

	}
	//to get list of all feedbacks
	@GetMapping("/allFeedbacks")
	public ResponseEntity<List<Feedback>> getAllFeedbacks(){
		List<Feedback> feedbacks=feedbackService.getAllFeedbacksOrderByMerchantId();
		System.out.println(feedbacks);
		return new ResponseEntity<List<Feedback>>(feedbacks,HttpStatus.OK);
	}
	//to get number of feedbacks per merchant
	@GetMapping("/getNumberOfFeedbacksPerMerchant")
	public ResponseEntity<List<Long>> getNumberOfFeedbacksPerMerchant(){
		List<Long> feedbacks=feedbackService.getNumberOfFeedbacksPerMerchant();
		System.out.println("dsfs"+feedbacks);
		return new ResponseEntity<List<Long>>(feedbacks,HttpStatus.OK);
	}
	
	//to get all feedbacks coressponding to a particular product id
	@GetMapping("/feedback/{productId}")
	public ResponseEntity<List<Feedback>> getAllFeedbacks(@PathVariable int productId){
		List<Feedback> feedback=feedbackService.getAllFeedbacks(productId);
		
		return new ResponseEntity<List<Feedback>>(feedback,HttpStatus.OK);
	
	}
	//to get feedback list for a particular merchant
	@GetMapping("/merchantFeedback/{mailId}")
	public ResponseEntity<List<Feedback>> getMerchantFeedbacks(@PathVariable("mailId") String mailId){
		
		Merchant merchant=merchantService.getMerchantByMail(mailId);
		
		List<Feedback> feedbacks=feedbackService.getAllFeedbacksOfMerchant(merchant.getMerchantId());
		
		return new ResponseEntity<List<Feedback>>(feedbacks,HttpStatus.OK);

	}
	
	
	//average rating function
	@GetMapping("/averageRating/{mailId}")
	public ResponseEntity<Double> averageMerchantRating(@PathVariable("mailId") String mailId){
		
		Merchant merchant=merchantService.getMerchantByMail(mailId);
	
		Double averageRating=feedbackService.calculateMerchantRating(merchant.getMerchantId());
		
		return new ResponseEntity<Double>(averageRating,HttpStatus.OK);

	}
}