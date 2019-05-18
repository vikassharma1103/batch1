package com.capstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.dao.IYouMailDao;
import com.capstore.model.Email;
import com.capstore.model.YouMailLogin;

@Service("youMailService")
public class YouMailService implements IYouMailService{
	
	@Autowired
	IYouMailDao youMailDao;

	@Override
	public YouMailLogin validateLogin(YouMailLogin login) {
		// TODO Auto-generated method stub
		return youMailDao.getByEmailIdAndPassword(login.getEmailId(), login.getPassword());
	}

	
	
	

}
