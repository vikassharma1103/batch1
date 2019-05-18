package com.capstore.service;

import java.util.List;

import com.capstore.model.Return;

public interface IReturnService {
	
	public List<Return> getAllReturnDetails();
	
	public int addrecordtoreturn(int temp);
	
	public Return checkstatus(int orderid);
	

	public boolean refundMoney(int orderId);

	public List<Return> getreturngoods();

}
