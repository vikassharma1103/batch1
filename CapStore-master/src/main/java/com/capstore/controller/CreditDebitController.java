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

import com.capstore.model.CreditDebit;
import com.capstore.service.ICreditDebitService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class CreditDebitController {

	@Autowired
	private ICreditDebitService creditDebitService;

	@PostMapping("/card")
	public ResponseEntity<Boolean> saveCard(@RequestBody CreditDebit card) {
		if (creditDebitService.saveCard(card)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/card")
	public ResponseEntity<List<CreditDebit>> getCard() {
		List<CreditDebit> cards = creditDebitService.getAllCards();
		if (cards.isEmpty()) {
			return new ResponseEntity(cards, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<CreditDebit>>(cards, HttpStatus.OK);
		}
	}

	@GetMapping("/card/number/{cardNumber}")
	public ResponseEntity<CreditDebit> getCardFromCardNumber(@PathVariable Long cardNumber) {
		CreditDebit creditDebit = creditDebitService.getCardFromCardNumber(cardNumber);
		if (creditDebit == null) {
			return new ResponseEntity("Sorry! Card does not exist!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CreditDebit>(creditDebit, HttpStatus.OK);
	}

	@PutMapping("/card/deposit/{amount}")
	public ResponseEntity<Boolean> depositAmount(@RequestBody CreditDebit card, @PathVariable double amount) {
		if (creditDebitService.depositAmount(amount, card)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}

	@PutMapping("/card/withdraw/{amount}")
	public ResponseEntity<Boolean> withdrawAmount(@RequestBody CreditDebit card, @PathVariable double amount) {
		if (!creditDebitService.isValidCard(card)) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		if (creditDebitService.withdrawAmount(amount, card)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}
}
