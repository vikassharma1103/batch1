package com.capstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.dao.ITransactionDao;
import com.capstore.model.Coupons;
import com.capstore.model.Invoice;
import com.capstore.model.Order;
import com.capstore.model.Transaction;

@Service("transactionService")
public class TransactionService implements ITransactionService{

	@Autowired
	ITransactionDao transactionDao;
	
	@Autowired
	ICartService cartService;
	
	@Override
	public List<Transaction> getAllTransactions() {
		return transactionDao.findAll();
	}

	@Override
	public Transaction getTransaction(int transactionId) { //Team 6
		Optional<Transaction> optional = transactionDao.findById(transactionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public boolean insertTransaction(Transaction transaction) {//Team 6
		transactionDao.save(transaction);
		return true;
	}

	@Override
	public boolean updateTransaction(Transaction transaction) {//Team 6
		transactionDao.save(transaction);
		return true;
	}

	@Override
	public double calculateFinalAmountForPayment(Order order) {
		
		double totalAmount = cartService.calculateTotalCartAmount(order.getCart());
		Coupons coupon = order.getCoupon();
		
		if(coupon == null) {
			return totalAmount;
		}else {
			int discount = coupon.getDiscountPercentage();
			double maxDiscount = coupon.getMaxDiscount();
			
			double discountProvided = totalAmount*((double)discount)/100.0;
			
			if(discountProvided>maxDiscount) {
				discountProvided = maxDiscount;
			}
			
			return (totalAmount-discountProvided);
		}
	}
	
	@Override
	public Transaction getTransactionFromInvoice(Invoice invoice) {
		
		return transactionDao.getTransactionFromInvoice(invoice);
	}
	
	
}


