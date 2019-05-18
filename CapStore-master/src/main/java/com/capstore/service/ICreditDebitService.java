package com.capstore.service;

import java.util.List;

import com.capstore.model.CreditDebit;

public interface ICreditDebitService {

	public boolean saveCard(CreditDebit creditDebit);
	public List<CreditDebit> getAllCards();
	public CreditDebit getCardFromCardNumber(long cardNumber);
	public boolean isValidCard(CreditDebit creditDebit);
	public boolean depositAmount(double amount, CreditDebit creditDebit);

	public boolean withdrawAmount(double amount, CreditDebit creditDebit);
	
}
