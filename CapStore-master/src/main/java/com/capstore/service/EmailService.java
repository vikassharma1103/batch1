package com.capstore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.dao.IEmailDao;
import com.capstore.model.Customer;
import com.capstore.model.Email;

@Service("emailService")
public class EmailService implements IEmailService{

	@Autowired

	private IEmailDao emailDao;
	
	

	@Override
	public void sendEmailToCustomer(Email email) {
		
		emailDao.save(email);
		
	}

	@Override
	public List<Customer> getCustomerList() {
		
		return emailDao.getCustomerList();
	}

	@Override
	public void sendEmail(Email mail) {
		emailDao.save(mail);
	}
	
	@Override
	public List<Email> getEmails(String emailId) {
		// TODO Auto-generated method stub
		return emailDao.getByReceiverEmailId(emailId);
	}
}