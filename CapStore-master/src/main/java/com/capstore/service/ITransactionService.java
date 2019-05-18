package com.capstore.service;

import java.util.List;

import com.capstore.model.Invoice;
import com.capstore.model.Order;
import com.capstore.model.Transaction;

public interface ITransactionService {
	
	public List<Transaction> getAllTransactions();	
	public Transaction getTransaction(int transactionId);

	public boolean insertTransaction(Transaction transaction);
	public boolean updateTransaction(Transaction transaction);
	
	public double calculateFinalAmountForPayment(Order order);
	public Transaction getTransactionFromInvoice(Invoice invoice);	
}