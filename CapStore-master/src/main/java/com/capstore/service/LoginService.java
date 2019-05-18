package com.capstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.dao.ICustomerDao;
import com.capstore.dao.ILoginDao;
import com.capstore.model.Customer;
import com.capstore.model.Login;


@Service("loginService")
public class LoginService implements ILoginService{
	
@Autowired
	private ILoginDao loginDao;
	
@Autowired
	private ICustomerDao customerDao;
	@Override
	public Login getLogin(String emailId, String password) {
		// TODO Auto-generated method stub
		Login login=loginDao.getByEmailIdAndPassword( emailId, password);
		System.out.println(login);
		return login;
	}

	@Override
	public Customer getCustomerId(String emailId) {
		
		return customerDao.getByEmailId(emailId);
		
	}
	



	@Override
	public boolean setPasswordByEmail(Login login) {
		  Login login1=loginDao.getByEmailId(login.getEmailId());
		   System.out.println(login1);
		   if(login1==null)
		   {
			   return false;
		   }
		   else
		   {
		   login1.setPassword(login.getPassword());
			System.out.println(login1);
		   loginDao.save(login1);
		   return true;
		   }
		
	}

	@Override
	public Login getLoginByEmailId(String emailId) {
		// TODO Auto-generated method stub
		
		return loginDao.getByEmailId(emailId);
			
		
	}

	@Override
	public void updateLogin(Login login) {
		// TODO Auto-generated method stub
		
		 loginDao.save(login);
		
	}

	@Override
	public void remove(String emailId) {
		loginDao.deleteByEmailId(emailId);
	}
}
