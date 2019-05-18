package com.capstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstore.model.Customer;
import com.capstore.model.Email;
import com.capstore.model.YouMailLogin;
import com.capstore.service.IEmailService;
import com.capstore.service.IYouMailService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1")
public class YouMailController {

	@Autowired
	IYouMailService youMailService;
	
	@Autowired
	IEmailService emailService;

	@PostMapping("/youMail")
	public ResponseEntity<Boolean> validateYouMailLogin(@RequestBody YouMailLogin login){

		YouMailLogin youMailLogin=youMailService.validateLogin(login);
		if(youMailLogin!=null)
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		else
		{
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
	}
	
	
	@PostMapping("/youMail/email")
	public ResponseEntity<List<Email>> sendVerificationToMail(@RequestBody String email)
	{
		
		List<Email> emails= emailService.getEmails(email);
		return new ResponseEntity<List<Email>>(emails, HttpStatus.OK);

	}
	
}
