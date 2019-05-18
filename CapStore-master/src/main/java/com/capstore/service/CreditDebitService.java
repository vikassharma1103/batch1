package com.capstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.dao.ICreditDebitDao;
import com.capstore.model.CreditDebit;

@Service("creditDebitService")
public class CreditDebitService implements ICreditDebitService {

	@Autowired
	private ICreditDebitDao creditDebitDao;

	@Override
	public boolean saveCard(CreditDebit creditDebit) {
		creditDebitDao.save(creditDebit);
		return true;
	}

	@Override
	public List<CreditDebit> getAllCards() {
		return creditDebitDao.findAll();
	}
	
	@Override
	public CreditDebit getCardFromCardNumber(long cardNumber) {
		Optional<CreditDebit> optional = creditDebitDao.findById(cardNumber);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public boolean isValidCard(CreditDebit creditDebit) {
		System.out.println(creditDebit);
		CreditDebit card = getCardFromCardNumber(creditDebit.getCardNumber());
		System.out.println(card);
		if(!creditDebit.getCardHolderName().equalsIgnoreCase(card.getCardHolderName())) {
			return false;
		}
		if(creditDebit.getCvv()!=card.getCvv()) {
			return false;
		}
		/*if(creditDebit.getExpiryDate().getMonth()!=card.getExpiryDate().getMonth()) {
			return false;
		}
		if(creditDebit.getExpiryDate().getYear()!=card.getExpiryDate().getYear()) {
			return false;
		}*/
		if(creditDebit.getPinNumber()!=card.getPinNumber()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean depositAmount(double amount, CreditDebit creditDebit) {
		CreditDebit card = getCardFromCardNumber(creditDebit.getCardNumber());
		if(card!=null) {
			double finalAmount = card.getBalance() + amount;
			card.setBalance(finalAmount);
			creditDebitDao.save(card);
			return true;
		}
		return false;
	}

	@Override
	public boolean withdrawAmount(double amount, CreditDebit creditDebit) {
		if(!isValidCard(creditDebit)) {
			System.out.println("inval card");
			return false;
		}
		CreditDebit card = getCardFromCardNumber(creditDebit.getCardNumber());
		if(card!=null) {
			System.out.println("card sc");
			double balance = card.getBalance();
			if (amount > balance) {
				System.out.println("insu null");
				return false;
			}
			double finalAmount = balance - amount;
			card.setBalance(finalAmount);
			creditDebitDao.save(card);
			System.out.println("sc");
			return true;
		}
		return false;
	}

}
