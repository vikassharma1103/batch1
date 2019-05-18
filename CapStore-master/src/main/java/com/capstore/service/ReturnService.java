package com.capstore.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.dao.IOrderDao;
import com.capstore.dao.IReturnDao;
import com.capstore.model.Invoice;
import com.capstore.model.Order;
import com.capstore.model.Return;
import com.capstore.model.Transaction;

@Service("returnService")
public class ReturnService implements IReturnService{

	@Autowired
	public IReturnDao returnDao;
	

	@Autowired
	IOrderDao orderDao;
	
	

	@Autowired
	IInvoiceService invoiceService;
	
	@Autowired
	ITransactionService transactionService;
	
	@Autowired
	IBankAccountService bankAccountService;
	
	@Autowired
	ICreditDebitService creditDebitService;
	
	
	@Override
	public List<Return> getAllReturnDetails() {
		return returnDao.findAll();
	}

	@Override
	public int addrecordtoreturn(int temp) {
		// TODO Auto-generated method stub
       
		Return myreturn = new Return();
		
		Order myorder=orderDao.findById(temp).get();
		
		myreturn.setOrder(myorder);
		myreturn.setPickupDate(new Date());
		
		myreturn.setReturnStatus("success");
		
		
	 //  myreturn.setCartproduct(myorder.getCart().getCartProducts());
		
	   returnDao.save(myreturn);
	   
	   return myreturn.getReturnId();
	   
	}
	
	@Override
	public Return checkstatus(int orderid) {
		// TODO Auto-generated method stub
	
		
		Order myorder=orderDao.findById(orderid).get();
		List<Return> prodreturn = returnDao.findAll();
		for(Return ret:prodreturn)
		{
			if(ret.getOrder().getOrderId()==orderid)
			{
				return ret;
			}
		}
		
		return null;
  	}
	
	
	@Override
	public boolean refundMoney(int orderId) {
		Invoice invoice = invoiceService.getInvoiceFromOrderId(orderId);
		Transaction transaction = transactionService.getTransactionFromInvoice(invoice);
		double transactionAmount = invoice.getDiscountedPrice();
		bankAccountService.withdrawAmount(transactionAmount, bankAccountService.getCapstoreBankAccount());
		if(transaction.getModeOfPayment().equals("NET_BANKING")) {
			
			bankAccountService.depositAmount(transactionAmount, bankAccountService.getBankAccount(transaction.getPaymentModeNumber()));
			return true;
			
		}else if(transaction.getModeOfPayment().equals("CARD")) {
			
			creditDebitService.depositAmount(transactionAmount, creditDebitService.getCardFromCardNumber(transaction.getPaymentModeNumber()));
			return true;
			
		}else if(transaction.getModeOfPayment().equals("CASH")) {
			return true;
		}else {
			return false;
		}
	}


	@Override
	public List<Return> getreturngoods() {
		// TODO Auto-generated method stub
		return returnDao.findAll();
	}


	

}



