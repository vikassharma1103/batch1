package com.capstore.service;

import java.util.List;

import com.capstore.model.Email;
import com.capstore.model.YouMailLogin;

public interface IYouMailService {

	YouMailLogin validateLogin(YouMailLogin login);

	

}
