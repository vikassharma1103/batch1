package com.capstore.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.dao.IFeedbackDao;
import com.capstore.model.Feedback;

@Service("feedbackService")
public class FeedbackService implements IFeedbackService{

	@Autowired
	private IFeedbackDao feedbackDao;
	
	@Override
	public void submitFeedback(Feedback feedback) {
		feedbackDao.save(feedback);
	}

	@Override
	public double calculateProductRating(int productId) {
		
		double averageRating = 0;
		int sum=0;
		int feedbacksNumber=0;
		
		List<Feedback> feedbacks = feedbackDao.findByProductId(productId);
		
		for(Feedback myFeedback:feedbacks) {
				
			sum += myFeedback.getRatingProduct();
			feedbacksNumber++;
		}
		
		if(feedbacksNumber!=0) {
			averageRating = (double)((double)sum/feedbacksNumber);
		}
		
		return averageRating;
	}

	@Override
	public double calculateMerchantRating(int merchantId) {
		
		double averageRating = 0;
		int sum=0;
		int feedbacksNumber=0;
		
		List<Feedback> feedbacks = feedbackDao.findByMerchantId(merchantId);
		
		for(Feedback myFeedback:feedbacks) {
				
			sum += myFeedback.getRatingMerchant();
			feedbacksNumber++;
		}
		
		if(feedbacksNumber!=0) {
			averageRating = (double)((double)sum/feedbacksNumber);
		}
		
		return averageRating;
	}

	@Override
	public List<Feedback> getAllFeedbacksOrderByMerchantId() {
		return feedbackDao.findAllOrderByMerchantId();
	}

	@Override
	public List<Long> getNumberOfFeedbacksPerMerchant() {
		//List<Feedback> feedbacks=feedbackDao.findAllOrderByMerchantId();
		List<Long> numberOfFeedbacksPerMerchant=feedbackDao.numberOfFeedbacksPerMerchant();//new LinkedList<Integer>();
		return numberOfFeedbacksPerMerchant;
	}

	
	public List<Feedback> getAllFeedbacks(int productId){
		
		 return feedbackDao.getAllFeedbacks(productId);
			
		}

	@Override
	public List<Feedback> getAllFeedbacksOfMerchant(int merchantId) {
		
		return feedbackDao.findByMerchantId(merchantId);
	}

	
}