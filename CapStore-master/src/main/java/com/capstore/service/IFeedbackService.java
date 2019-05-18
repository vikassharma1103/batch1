package com.capstore.service;

import java.util.List;

import com.capstore.model.Feedback;

public interface IFeedbackService {

	public void submitFeedback(Feedback feedback);
	
	public double calculateProductRating(int productId);
	
	public double calculateMerchantRating(int merchantId);
	public List<Feedback> getAllFeedbacks(int productId);

	public List<Feedback> getAllFeedbacksOrderByMerchantId();

	public List<Long> getNumberOfFeedbacksPerMerchant();

	public List<Feedback> getAllFeedbacksOfMerchant(int merchantId);

	

}
