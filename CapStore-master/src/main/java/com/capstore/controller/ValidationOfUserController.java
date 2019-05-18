package com.capstore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.usertype.UserType;
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

import com.capstore.model.Address;
import com.capstore.model.Customer;
import com.capstore.model.Email;
import com.capstore.model.Login;
import com.capstore.model.Merchant;
import com.capstore.service.ICustomerService;
import com.capstore.service.IEmailService;
import com.capstore.service.ILoginService;
import com.capstore.service.IMerchantService;
import com.capstore.service.LoginService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1")
public class ValidationOfUserController {

	
	Address address=new Address();
	@Autowired
	IEmailService emailService;

	@Autowired
	IMerchantService merchantService;

	@Autowired
	ICustomerService customerService;

	@Autowired
	ILoginService loginService;
	
	
	@PostMapping("/addAddress")
	public ResponseEntity<Boolean> AddAddress(@RequestBody Address address){
	
		this.address=address;
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		
	}

	//customer validation!!
	@PostMapping("/sendVerificationMail")
	public ResponseEntity<Boolean> sendVerificationMail(@RequestBody Customer customer){
		
			try
			{
			if(loginService.getLoginByEmailId(customer.getEmailId())==null) {

				customer.getAddresses().add(this.address);
				customerService.createCustomer(customer);
				
				Email mail=new Email();
				mail.setReceiverEmailId(customer.getEmailId());
				mail.setSenderEmailId("admin@capstore.com");
				mail.setMessage("Verify by clicking this link ");
				mail.setLink("//localhost:4200/auth/sign-in");
				emailService.sendEmail(mail);
				return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<Boolean>(false,HttpStatus.OK);
			}
			}
			catch(Exception e)
			{
				return new ResponseEntity<Boolean>(false,HttpStatus.OK);
			}

			
		

	}


	@PostMapping("/emailVerificationDone")
	public ResponseEntity<Boolean> emailVerificationDone(@RequestBody Email email){

		try {
			
		System.out.println(email);
		Customer customer=customerService.getCustomerByEmail(email.getReceiverEmailId());

		customer.setVerified(true);
		customerService.updateCustomer(customer);


		Login login=new Login();
		login.setEmailId(customer.getEmailId());
		login.setPassword(customer.getPassword());
		login.setUserTypes("CUSTOMER");
		
		loginService.updateLogin(login);
		System.out.println(customerService.getAllCustomers());

		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
	}

		//merchant validation!!
		/*@GetMapping("/merchantVerification/{merchantMail}")
		public ResponseEntity<Boolean> merchantVerification(@PathVariable("merchantMail") String merchantMail){
			
			Merchant merchant=merchantService.getMerchantByMail(merchantMail);
			
			merchant.setVerified(true);
			merchantService.updateMerchant(merchant);
			Login login=new Login();
			login.setEmailId(merchant.getEmailId());
			login.setPassword(merchant.getMerchantPassword());
			login.setUserTypes("MERCHANT");
			loginService.updateLogin(login);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}*/


	

}
