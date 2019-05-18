package com.capstore.service;

import java.util.List;

import com.capstore.model.BankAccount;

public interface IBankAccountService {

	public boolean addBankAccount(BankAccount bankAccount);

	public List<BankAccount> getAllBankAccounts();
	public BankAccount getBankAccount(long accountNumber);
	public BankAccount getCapstoreBankAccount();

	public BankAccount getBankAccountFromUserNamePassword(String userName, String userPassword);

	public boolean depositAmount(double amount, BankAccount bankAccount);

	public boolean withdrawAmount(double amount, BankAccount bankAccount);
}
