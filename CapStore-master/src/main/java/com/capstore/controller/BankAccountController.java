package com.capstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstore.model.BankAccount;
import com.capstore.service.IBankAccountService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class BankAccountController {

	@Autowired
	private IBankAccountService bankAccountService;

	@PostMapping("/bankaccount")
	public ResponseEntity<Boolean> addBankAccount(@RequestBody BankAccount bankAccount) {
		if (bankAccountService.addBankAccount(bankAccount)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/bankaccount")
	public ResponseEntity<List<BankAccount>> getAllBankAccounts() {
		List<BankAccount> bankAccounts = bankAccountService.getAllBankAccounts();
		if (bankAccounts.isEmpty()) {
			return new ResponseEntity(bankAccounts, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<BankAccount>>(bankAccounts, HttpStatus.OK);
		}
	}

	@GetMapping("/bankaccount/credential")
	public ResponseEntity<BankAccount> getBankAccountsByCredintial(@RequestBody BankAccount bankAccount) {
		BankAccount account = bankAccountService.getBankAccountFromUserNamePassword(bankAccount.getUserName(), bankAccount.getUserPassword());

		return new ResponseEntity<BankAccount>(account, HttpStatus.OK);
	}
	
	/*
	 * @PostMapping("/bankaccount/credential") public ResponseEntity<BankAccount>
	 * getBankAccountFromUserNamePassword(@RequestBody String userName,
	 * 
	 * @RequestBody String userPassword) { BankAccount bankAccount =
	 * bankAccountService.getBankAccountFromUserNamePassword(userName,
	 * userPassword); if (bankAccount==null) { return new
	 * ResponseEntity("Sorry! No such user exists!", HttpStatus.NOT_FOUND); } return
	 * new ResponseEntity<BankAccount>(bankAccount, HttpStatus.OK); }
	 */

	@PutMapping("/bankaccount/deposit/{amount}")
	public ResponseEntity<Boolean> depositAmount(@RequestBody BankAccount account, @PathVariable double amount) {
		if (bankAccountService.depositAmount(amount, account)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}

	@PutMapping("/bankaccount/withdraw/{amount}")
	public ResponseEntity<Boolean> withdrawAmount(@RequestBody BankAccount account, @PathVariable double amount) {
		if (bankAccountService.withdrawAmount(amount, account)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}
}
