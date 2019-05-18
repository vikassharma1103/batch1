package com.capstore.service;


import java.util.List;

import com.capstore.model.Customer;
import com.capstore.model.Email;

public interface IEmailService {


	public void sendEmailToCustomer(Email email);

	public List<Customer> getCustomerList();

	public void sendEmail(Email mail);

	List<Email> getEmails(String emailId);
	
	
  
}
