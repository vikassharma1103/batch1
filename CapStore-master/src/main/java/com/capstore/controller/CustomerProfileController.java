package com.capstore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.capstore.model.Customer;
import com.capstore.model.Login;
import com.capstore.model.Merchant;
import com.capstore.service.ICustomerService;
import com.capstore.service.ILoginService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1")
public class CustomerProfileController {
	
	@Autowired
	ICustomerService customerService;
	
	
	@Autowired
	ILoginService loginService;
	
	@GetMapping("/getCustomers/{email}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("email") String email)
	{
		
		Customer customer=customerService.getCustomerByEmail(email);
		System.out.println(customer);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}

	
	
	@PostMapping("/customerPasswordMatch/{email}")
	public ResponseEntity<Boolean> passwordMatch(@RequestBody String password,HttpSession session, @PathVariable("email") String mail) {


		Login login=loginService.getLoginByEmailId(mail);
		System.out.println(login);
		if(login.getPassword().equals(password)) {
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
		
		



	}

	@PostMapping("/customerPasswordChange/{email}")
	public ResponseEntity<Boolean> passwordChange(@RequestBody String password, HttpServletRequest request, @PathVariable("email") String mail) {

		
		//String merchantMail=(String) session.getAttribute("email");
		Customer customer=customerService.getCustomerByEmail(mail);
		customer.setPassword(password);
		customerService.updateCustomer(customer);
		Login login=loginService.getLoginByEmailId(mail);
		login.setPassword(password);
		loginService.updateLogin(login);
		
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);




	}
	
	@PutMapping("/updatemobile/{mail}")
	public ResponseEntity<Boolean> updateMobile(@RequestBody Customer customer,  @PathVariable("mail") String mail){
		Customer cust=customerService.getCustomerByEmail(mail);
		cust.setMobileNumber(customer.getMobileNumber());
	Boolean flag= customerService.updateMobile(cust);
	
	return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	} 
	
	
	

}
