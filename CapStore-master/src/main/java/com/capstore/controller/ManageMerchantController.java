package com.capstore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capstore.model.Address;
import com.capstore.model.Login;
import com.capstore.model.Merchant;
import com.capstore.model.Product;
import com.capstore.service.FeedbackService;
import com.capstore.service.ILoginService;
import com.capstore.service.IMerchantService;
import com.capstore.service.IProductService;
import com.capstore.service.StorageService;

@CrossOrigin(origins="*")
@RestController()
@RequestMapping("/api/v1")
public class ManageMerchantController {

	
	Address address=new Address();
	@Autowired
	IMerchantService merchantService;
	
	@Autowired
	ILoginService loginService;
	@Autowired
	StorageService storageService;
	@Autowired
	IProductService productService;

	public FeedbackService feedbackService;

	
	
	@PostMapping("/addAddressMerchant")
	public ResponseEntity<Boolean> AddAddress(@RequestBody Address address){
	this.address=address;
	return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
	@PostMapping("/merchantRegistration")
	public ResponseEntity<Boolean> addMerchant(
	@RequestBody Merchant merchant){
	try
	{
	merchant.setMerchantAddress(address);
	merchantService.addMerchant(merchant);
	return new ResponseEntity<>(true, HttpStatus.OK);
	}
	catch(Exception e)
	{
	return new ResponseEntity<>(false, HttpStatus.OK);
	}
	// {
	// return new ResponseEntity<>(false, HttpStatus.OK);
	// }
	}
	
	//for deleting a merchant
	@DeleteMapping("/merchants/{merchantId}")
	public ResponseEntity<Boolean> deleteMerchant(@PathVariable Integer merchantId){

		merchantService.deleteMerchant(merchantId);
		return new ResponseEntity<>(true,HttpStatus.OK);
	}
	//for retrieving merchant rating
	@GetMapping("/merchants/{merchantId}")
	public ResponseEntity<Double> getMerchantRating(@PathVariable int merchantId) {

		double avgMerchantRating=merchantService.getMerchantRating(merchantId);

		return new ResponseEntity<Double>(avgMerchantRating,HttpStatus.OK);

	}
	
	@PostMapping("/passwordMatch/{email}")
	public ResponseEntity<Boolean> passwordMatch(@RequestBody String pasword,HttpSession session, @PathVariable("email") String mail) {

		
		Login login=loginService.getLoginByEmailId(mail);
		System.out.println(login);
		if(login.getPassword().equals(pasword)) {
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}



	}

	@PostMapping("/passwordChange/{email}")
	public ResponseEntity<Boolean> passwordChange(@RequestBody String password, HttpServletRequest request, @PathVariable("email") String mail) {

		
		//String merchantMail=(String) session.getAttribute("email");
		Merchant merchant=merchantService.getMerchantByMail(mail);
		merchant.setMerchantPassword(password);
		merchantService.updateMerchant(merchant);
		Login login=loginService.getLoginByEmailId(mail);
		login.setPassword(password);
		loginService.updateLogin(login);
		
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);




	}
	
	//-------------------------------------Upload Images---------------------------------------------
List<String> files = new ArrayList<String>();
	
	@PostMapping("/postMerchant/{productId}")
	public ResponseEntity<String> handleFileUpload(@PathVariable("productId") String productId,@RequestParam("file") MultipartFile file) {
		
		String message = "";
		try {
			//System.out.println(productId);
			storageService.store(file,productId);
			files.add(file.getOriginalFilename());
           // System.out.println(files);
			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
	@PostMapping("/sliderMerchant/{productId}/{Id}")
	public ResponseEntity<String> handleSliderUpload(@PathVariable("productId") String productId,@PathVariable("Id") String Id,@RequestParam("file") MultipartFile file) {
		
		String message = "";
		try {
			System.out.println(productId);
			storageService.storeSlider(file,productId,Id);
			files.add(file.getOriginalFilename());
           // System.out.println(files);
			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
	
	
	
	@GetMapping("/viewMerchantProducts/{emailId}")
	public ResponseEntity<List<Product>> getMerchantProducts(@PathVariable("emailId")String emailId){
		
		//System.out.println("VIEWproducts");
		List<Product> products=productService.getMerchantProducts(emailId);
		if(products.isEmpty())
			 return new ResponseEntity("Sorry ! Inventories not available!",HttpStatus.NOT_FOUND);
		
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		
	}
	
	

	




}
