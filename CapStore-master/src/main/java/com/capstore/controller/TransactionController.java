package com.capstore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.capstore.model.CreditDebit;
import com.capstore.model.Invoice;
import com.capstore.model.Order;
import com.capstore.model.Transaction;
import com.capstore.service.IBankAccountService;
import com.capstore.service.ICartService;
import com.capstore.service.ICreditDebitService;
import com.capstore.service.IInvoiceService;
import com.capstore.service.IOrderService;
import com.capstore.service.ITransactionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class TransactionController {

	@Autowired
	ITransactionService transactionService;

	@Autowired
	IBankAccountService bankAccountService;

	@Autowired
	ICreditDebitService creditDebitService;

	@Autowired
	IInvoiceService invoiceService;

	@Autowired
	IOrderService orderService;
	
	@Autowired
	ICartService cartService;

	@GetMapping("/transaction")//
	public ResponseEntity<List<Transaction>> getAllTransactions(HttpSession session) {// Team 6
		List<Transaction> transactions = transactionService.getAllTransactions();
		if (transactions.isEmpty()) {
			return new ResponseEntity("Transactions not found", HttpStatus.OK);
		}
		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
	}

	@GetMapping("/transaction/{transactionId}")//
	public ResponseEntity<Transaction> getTransaction(@PathVariable("transactionId") Integer transactionId) {// Team 6
		Transaction transaction = transactionService.getTransaction(transactionId);
		if (transaction == null)
			return new ResponseEntity("Sorry! Transaction is not available!", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
	}

	@PostMapping("/transaction")//
	public ResponseEntity<Boolean> insertTransaction(@RequestBody Transaction transaction) {// Team 6
		if (transactionService.insertTransaction(transaction)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}

	@PutMapping("/transaction")//
	public ResponseEntity<Boolean> updateTransaction(@RequestBody Transaction transaction) {// Team 6
		if (transactionService.updateTransaction(transaction)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}

	@PostMapping("/transaction/pay/bankaccount/order/{orderId}") //
	public ResponseEntity<Invoice> payByNetBanking(@RequestBody BankAccount account,@PathVariable int orderId) {// Team 6
		Invoice nullInvoice = null;
		BankAccount bankAccount = bankAccountService.getBankAccountFromUserNamePassword(account.getUserName(),
				account.getUserPassword());
		if (bankAccount == null) {
			orderService.deleteOrder(orderId);
			return new ResponseEntity<Invoice>(nullInvoice, HttpStatus.NOT_FOUND);
		}
		
		Order order = orderService.findOrderById(orderId);
		if(order==null) {
			return new ResponseEntity<Invoice>(nullInvoice, HttpStatus.NOT_FOUND);
		}
		
		double totalAmount = cartService.calculateTotalCartAmount(order.getCart());
		double finalAmount = transactionService.calculateFinalAmountForPayment(order);
		
		if (!bankAccountService.withdrawAmount(finalAmount, bankAccount)) {
			orderService.deleteOrder(orderId);
			return new ResponseEntity<Invoice>(nullInvoice, HttpStatus.OK);
		}

		if (!bankAccountService.depositAmount(finalAmount, bankAccountService.getCapstoreBankAccount())) {
			orderService.deleteOrder(orderId);
			return new ResponseEntity<Invoice>(nullInvoice, HttpStatus.OK);
		}

		Invoice invoice = generateInvoice(order,totalAmount,finalAmount);
		addNetBankingTransaction(account,invoice);

		System.out.println(invoice);
		
		return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
	}

	@PostMapping("/transaction/pay/card/order/{orderId}")//
	public ResponseEntity<Invoice> payByCard(@RequestBody CreditDebit creditDebit, @PathVariable int orderId) {// Team 6
		Invoice nullInvoice = null;
		if (!creditDebitService.isValidCard(creditDebit)) {
			System.out.println("card invalid");
			orderService.deleteOrder(orderId);
			return new ResponseEntity<Invoice>(nullInvoice, HttpStatus.OK);
		}
		
		Order order = orderService.findOrderById(orderId);
		if(order==null) {
			System.out.println("order null");
			return new ResponseEntity<Invoice>(nullInvoice, HttpStatus.NOT_FOUND);
		}
		
		double totalAmount = cartService.calculateTotalCartAmount(order.getCart());
		double finalAmount = transactionService.calculateFinalAmountForPayment(order);
		
		CreditDebit card = creditDebitService.getCardFromCardNumber(creditDebit.getCardNumber());
		if (card == null) {
			System.out.println("card null");
			orderService.deleteOrder(orderId);
			return new ResponseEntity<Invoice>(nullInvoice, HttpStatus.OK);
		}
		if (!creditDebitService.withdrawAmount(finalAmount, card)) {
			System.out.println("wd null");
			orderService.deleteOrder(orderId);
			return new ResponseEntity<Invoice>(nullInvoice, HttpStatus.OK);
		}

		if (!bankAccountService.depositAmount(finalAmount, bankAccountService.getCapstoreBankAccount())) {
			System.out.println("deposirt null");
			orderService.deleteOrder(orderId);
			return new ResponseEntity<Invoice>(nullInvoice, HttpStatus.OK);
		}

		Invoice invoice = generateInvoice(order,totalAmount,finalAmount);
		addCardTransaction(card,invoice);
		
		System.out.println(invoice);

		return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
	}

	@PostMapping("/transaction/pay/cash/order/{orderId}")//
	public ResponseEntity<Invoice> payByCard(@PathVariable int orderId) {// Team 6
		Invoice nullInvoice = null;
		Order order = orderService.findOrderById(orderId);
		if(order==null) {
			return new ResponseEntity<Invoice>(nullInvoice, HttpStatus.NOT_FOUND);
		}
		
		double totalAmount = cartService.calculateTotalCartAmount(order.getCart());
		double finalAmount = transactionService.calculateFinalAmountForPayment(order);
		
		if (!bankAccountService.depositAmount(finalAmount, bankAccountService.getCapstoreBankAccount())) {
			orderService.deleteOrder(orderId);
			return new ResponseEntity<Invoice>(nullInvoice, HttpStatus.OK);
		}

		Invoice invoice = generateInvoice(order,totalAmount,finalAmount);
		addCashTransaction(invoice);

		return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
	}

	private Invoice generateInvoice(Order order,double totalAmount,double finalAmount) {
		Invoice invoice = new Invoice();
		invoice.setOrder(order);
		invoice.setInvoiceDate(order.getOrderDate());
		invoice.setDiscountedPrice(finalAmount);
		invoice.setDiscount(totalAmount-finalAmount);
		invoiceService.generateInvoice(invoice);
		return invoice;
	}
	
	private void addNetBankingTransaction(BankAccount account,Invoice invoice) {
		Transaction transaction = new Transaction();
		transaction.setModeOfPayment("net banking");
		transaction.setPaymentModeNumber(account.getAccountNumber());
		transaction.setStatus("success");
		transaction.setInvoice(invoice);
		transactionService.insertTransaction(transaction);
	}
	private void addCardTransaction(CreditDebit card,Invoice invoice) {
		Transaction transaction = new Transaction();
		transaction.setModeOfPayment("card");
		transaction.setPaymentModeNumber(card.getCardNumber());
		transaction.setStatus("success");
		transaction.setInvoice(invoice);
		transactionService.insertTransaction(transaction);
	}
	private void addCashTransaction(Invoice invoice) {
		Transaction transaction = new Transaction();
		transaction.setModeOfPayment("cash");
		transaction.setPaymentModeNumber(0);
		transaction.setStatus("success");
		transaction.setInvoice(invoice);
		transactionService.insertTransaction(transaction);
	}
}
