package com.capstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capstore.dao.IBankAccountDao;
import com.capstore.model.BankAccount;
import com.capstore.model.CreditDebit;

@Service("bankAccountService")
public class BankAccountService implements IBankAccountService {

	@Autowired
	private IBankAccountDao bankAccountDao;

	@Override
	public boolean addBankAccount(BankAccount bankAccount) {
		bankAccountDao.save(bankAccount);
		return true;
	}

	@Override
	public List<BankAccount> getAllBankAccounts() {

		return bankAccountDao.findAll();
	}

	@Override
	public BankAccount getBankAccount(long accountNumber) {
		Optional<BankAccount> optional = bankAccountDao.findById(accountNumber);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public BankAccount getCapstoreBankAccount() {
		Optional<BankAccount> optional = bankAccountDao.findById(1234l);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@Override
	public BankAccount getBankAccountFromUserNamePassword(String userName, String userPassword) {
		List<BankAccount> accounts = bankAccountDao.getBankAccountFromUserNamePassword(userName, userPassword);
		if(!accounts.isEmpty()) {
			return accounts.get(0);
		}else {
			return null;
		}
	}
	
	@Override
	public boolean depositAmount(double amount, BankAccount bankAccount) {
		BankAccount account = getBankAccount(bankAccount.getAccountNumber());
		if(account!=null) {
			double finalAmount = account.getBalance() + amount;
			account.setBalance(finalAmount);
			bankAccountDao.save(account);
			return true;
		}
		return false;
	}

	@Override
	public boolean withdrawAmount(double amount, BankAccount bankAccount) {
		BankAccount account = getBankAccount(bankAccount.getAccountNumber());
		System.out.println(account);
		if(account!=null) {
			double balance = account.getBalance();
			if (amount > balance) {
				System.out.println("insu");
				return false;
			}
			double finalAmount = balance - amount;
			account.setBalance(finalAmount);
			bankAccountDao.save(account);
			System.out.println("sccc");
			return true;
		}
		return false;
	}

}
