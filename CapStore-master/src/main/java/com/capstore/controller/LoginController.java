package com.capstore.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstore.model.Customer;
import com.capstore.model.Email;
import com.capstore.model.Login;
import com.capstore.service.ICustomerService;
import com.capstore.service.IEmailService;
import com.capstore.service.ILoginService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1")
public class LoginController {

	
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private ICustomerService customerService;
	
	
	@Autowired
	private IEmailService emailService;
	
	@PostMapping("/validlogin")
	public ResponseEntity<Login> getLogin (@RequestBody Login login){
		
		//(@RequestBody Login login,
		
		Login loginbean=loginService.getLogin(login.getEmailId(),login.getPassword());
		
		if(loginbean==null)
		{
			return new ResponseEntity<Login>(new Login(),HttpStatus.OK);	
		}
		/*this.emailId=loginbean.getEmailId();*/
		
		System.out.println(loginbean);
		
		
		return new ResponseEntity<Login>(loginbean,HttpStatus.OK);	
	}
	
	@PostMapping("/forgotPassword")
	public ResponseEntity<Boolean> forgotPassword(@RequestBody Login login){
		
		
		boolean flag=loginService.setPasswordByEmail(login);

		if(flag)
		{
		
		Email mail=new Email();
		mail.setReceiverEmailId(login.getEmailId());
		mail.setMessage("Your new password is capstore123");
		mail.setImageUrl("");
		emailService.sendEmail(mail);
		return  new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}
		
		else
		{
			return  new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
		//emailService.sendEmail(mail);
		
		
		
		
	}
	
	
}	
		/*this.emailId=loginbean.getEmailId();*/
