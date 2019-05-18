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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstore.model.Customer;
import com.capstore.service.CustomerService;
import com.capstore.service.ICustomerService;
import com.capstore.service.ILoginService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1")
public class RegisterController {
	
	
	@Autowired
	private ICustomerService customerService;
	
	@PostMapping(value="/createcustomers")
	public ResponseEntity<String> createCustomer(@RequestBody Customer customer, HttpSession session){
		System.out.println(session.getAttribute("emailId"));
        boolean success=customerService.createCustomer(customer);
        if(success)
        return new ResponseEntity<String>("customer registered successfully", HttpStatus.OK);		
        else
        	return new ResponseEntity<String>("customer registration failed", HttpStatus.NO_CONTENT);		

	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers()	{
		List<Customer> customers=customerService.getAllCustomers();
		
		if(customers.isEmpty())
			return new ResponseEntity("Sorry! No customers available!",HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
	}
	
	@GetMapping("/getcustomer/{emailId}")
	public ResponseEntity<Customer> getCustomerFromEmailId(@PathVariable("emailId") String emailId){
		Customer customer = customerService.getCustomerByEmail(emailId);
		
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

}
