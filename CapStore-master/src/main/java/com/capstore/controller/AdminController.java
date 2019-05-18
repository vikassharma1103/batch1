package com.capstore.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capstore.model.Customer;
import com.capstore.model.DispatchAnalysis;
import com.capstore.model.Email;
import com.capstore.model.Inventory;
import com.capstore.model.Login;
import com.capstore.model.Merchant;
import com.capstore.model.Product;
import com.capstore.model.Promos;
import com.capstore.model.SalesAnalysis;
import com.capstore.service.IBusinessAnalysisService;
import com.capstore.service.ICustomerService;
import com.capstore.service.IEmailService;
import com.capstore.service.IInventoryMerchantService;
import com.capstore.service.ILoginService;
import com.capstore.service.IMerchantService;
import com.capstore.service.IProductService;
import com.capstore.service.IPromoService;
import com.capstore.service.LoginService;
import com.capstore.service.StorageService;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class AdminController {
	
	public ICustomerService customerService;
	
	@Autowired
	IPromoService promoService;
	
	@Autowired
	IEmailService emailService;
	
	@Autowired
	ILoginService loginService;
	
	@Autowired
	public IMerchantService merchantService;
	
	@Autowired
	IInventoryMerchantService inventoryMerchantService;
	
	@Autowired
	StorageService storageService;
	
	@Autowired
	IProductService productService;
	
	@Autowired
	IBusinessAnalysisService businessAnalysisService;
	

	
//	**************************Customers**************************************

	@GetMapping(value = "/customers/all")
	public ResponseEntity<java.util.List<Customer>> getAllCustomers() {
		System.out.println("");
		java.util.List<Customer> list_of_customers = customerService.getAllCustomers();
		System.out.println("Controller"+list_of_customers);
		if (list_of_customers==null) {
			new ResponseEntity("No Customers found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<java.util.List<Customer>>(list_of_customers, HttpStatus.OK);
	}
	
	
	@DeleteMapping(value="/deleteCustomerByAdmin/{customerId}")
    public ResponseEntity<List<Customer>>deleteCustomer(@PathVariable("customerId")int customerId){
	
	   List<Customer> customers=customerService.deleteCustomer(customerId);
	
	   if(customers==null)
		  return new ResponseEntity("Sorry!! Customer Id not available!",HttpStatus.NOT_FOUND);
	
	return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
	
}
	
	
//	************************Merchants**********************************************
	
	
	@PostMapping("/inviteMerchant")
	public ResponseEntity<Boolean> inviteMerchant(@RequestBody String merchantMailId){
		if(merchantService.checkIfExists(merchantMailId)){
			
			Email email = new Email();
			email.setMessage("Please accept the Request!");
			email.setLink("//localhost:4200");
			email.setReceiverEmailId(merchantMailId);
			email.setSenderEmailId("admin@gmail.com");
			emailService.sendEmail(email);
			
			
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}else{
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}
	
	
	@GetMapping(value = "/merchants/all")
	public ResponseEntity<java.util.List<Merchant>> getAllMerchants() {
		System.out.println("");
		java.util.List<Merchant> list_of_merchants = merchantService.getAllMerchants();
		System.out.println("Controller"+list_of_merchants);
		if (list_of_merchants==null) {
			new ResponseEntity("No Merchants found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<java.util.List<Merchant>>(list_of_merchants, HttpStatus.OK);
		
	}
	
	//admin verifies Merchant by clicking on approve button
	@RequestMapping("/merchantVerification/{merchantId}")
	public ResponseEntity<List<Merchant>> verifyMerchant_On_clicking_Approve_BUTTON(@PathVariable("merchantId") int merchantId) {
		
		Merchant merchant = merchantService.getMerchantByMerchantId(merchantId);
		merchant.setVerified(true); 
		merchantService.updateMerchant(merchant);
		
		List<Merchant> list_of_verified_merchants=merchantService.getAllMerchants();
		  
		Login login = new Login();
	    login.setEmailId(merchant.getEmailId());
	    login.setPassword(merchant.getMerchantPassword());
	    login.setUserTypes("MERCHANT");
        		
		return new ResponseEntity<List<Merchant>>(list_of_verified_merchants,HttpStatus.OK);
	}
	
	
	//admin removes Merchant by clicking on reject button
	@GetMapping("/merchantReject/{merchantId}")
	public ResponseEntity<List<Merchant>> rejectMerchant_On_clicking_Reject_BUTTON(@PathVariable("merchantId") int merchantId){
		
		List<Merchant> list_of_verified_merchants=merchantService.getAllMerchants();
		
		Merchant merchant=merchantService.getMerchantByMerchantId(merchantId);
		System.out.println(merchant);
	    
	    System.out.println("\r\n" + 
	    		"                                                                                                                                                                                                                                                                                                          \r\n" + 
	    		"                                         dddddddd                                                                                                                                                                                         dddddddd                                                        \r\n" + 
	    		"               AAA                       d::::::d                        iiii                                                              jjjj                                               tttt                                        d::::::d     YYYYYYY       YYYYYYY                              \r\n" + 
	    		"              A:::A                      d::::::d                       i::::i                                                            j::::j                                           ttt:::t                                        d::::::d     Y:::::Y       Y:::::Y                              \r\n" + 
	    		"             A:::::A                     d::::::d                        iiii                                                              jjjj                                            t:::::t                                        d::::::d     Y:::::Y       Y:::::Y                              \r\n" + 
	    		"            A:::::::A                    d:::::d                                                                                                                                           t:::::t                                        d:::::d      Y::::::Y     Y::::::Y                              \r\n" + 
	    		"           A:::::::::A           ddddddddd:::::d   mmmmmmm    mmmmmmm  iiiiiinnnn  nnnnnnnn         rrrrr   rrrrrrrrr      eeeeeeeeeeee  jjjjjjj   eeeeeeeeeeee       cccccccccccccccttttttt:::::ttttttt       eeeeeeeeeeee       ddddddddd:::::d      YYY:::::Y   Y:::::YYooooooooooo  uuuuuu    uuuuuu  \r\n" + 
	    		"          A:::::A:::::A        dd::::::::::::::d mm:::::::m  m:::::::mmi:::::n:::nn::::::::nn       r::::rrr:::::::::r   ee::::::::::::eej:::::j ee::::::::::::ee   cc:::::::::::::::t:::::::::::::::::t     ee::::::::::::ee   dd::::::::::::::d         Y:::::Y Y:::::Yoo:::::::::::oou::::u    u::::u  \r\n" + 
	    		"         A:::::A A:::::A      d::::::::::::::::dm::::::::::mm::::::::::mi::::n::::::::::::::nn      r:::::::::::::::::r e::::::eeeee:::::ej::::je::::::eeeee:::::eec:::::::::::::::::t:::::::::::::::::t    e::::::eeeee:::::eed::::::::::::::::d          Y:::::Y:::::Yo:::::::::::::::u::::u    u::::u  \r\n" + 
	    		"        A:::::A   A:::::A    d:::::::ddddd:::::dm::::::::::::::::::::::mi::::nn:::::::::::::::n     rr::::::rrrrr::::::e::::::e     e:::::j::::e::::::e     e:::::c:::::::cccccc:::::tttttt:::::::tttttt   e::::::e     e:::::d:::::::ddddd:::::d           Y:::::::::Y o:::::ooooo:::::u::::u    u::::u  \r\n" + 
	    		"       A:::::A     A:::::A   d::::::d    d:::::dm:::::mmm::::::mmm:::::mi::::i n:::::nnnn:::::n      r:::::r     r:::::e:::::::eeeee::::::j::::e:::::::eeeee::::::c::::::c     ccccccc     t:::::t         e:::::::eeeee::::::d::::::d    d:::::d            Y:::::::Y  o::::o     o::::u::::u    u::::u  \r\n" + 
	    		"      A:::::AAAAAAAAA:::::A  d:::::d     d:::::dm::::m   m::::m   m::::mi::::i n::::n    n::::n      r:::::r     rrrrrre:::::::::::::::::ej::::e:::::::::::::::::ec:::::c                  t:::::t         e:::::::::::::::::ed:::::d     d:::::d             Y:::::Y   o::::o     o::::u::::u    u::::u  \r\n" + 
	    		"     A:::::::::::::::::::::A d:::::d     d:::::dm::::m   m::::m   m::::mi::::i n::::n    n::::n      r:::::r           e::::::eeeeeeeeeee j::::e::::::eeeeeeeeeee c:::::c                  t:::::t         e::::::eeeeeeeeeee d:::::d     d:::::d             Y:::::Y   o::::o     o::::u::::u    u::::u  \r\n" + 
	    		"    A:::::AAAAAAAAAAAAA:::::Ad:::::d     d:::::dm::::m   m::::m   m::::mi::::i n::::n    n::::n      r:::::r           e:::::::e          j::::e:::::::e          c::::::c     ccccccc     t:::::t    ttttte:::::::e          d:::::d     d:::::d             Y:::::Y   o::::o     o::::u:::::uuuu:::::u  \r\n" + 
	    		"   A:::::A             A:::::d::::::ddddd::::::dm::::m   m::::m   m::::i::::::in::::n    n::::n      r:::::r           e::::::::e         j::::e::::::::e         c:::::::cccccc:::::c     t::::::tttt:::::e::::::::e         d::::::ddddd::::::dd            Y:::::Y   o:::::ooooo:::::u:::::::::::::::uu\r\n" + 
	    		"  A:::::A               A:::::d:::::::::::::::::m::::m   m::::m   m::::i::::::in::::n    n::::n      r:::::r            e::::::::eeeeeeee j::::je::::::::eeeeeeee  c:::::::::::::::::c     tt::::::::::::::te::::::::eeeeeeee  d:::::::::::::::::d         YYYY:::::YYYYo:::::::::::::::ou:::::::::::::::u\r\n" + 
	    		" A:::::A                 A:::::d:::::::::ddd::::m::::m   m::::m   m::::i::::::in::::n    n::::n      r:::::r             ee:::::::::::::e j::::j ee:::::::::::::e   cc:::::::::::::::c       tt:::::::::::tt ee:::::::::::::e   d:::::::::ddd::::d         Y:::::::::::Y oo:::::::::::oo  uu::::::::uu:::u\r\n" + 
	    		"AAAAAAA                   AAAAAAddddddddd   ddddmmmmmm   mmmmmm   mmmmmiiiiiiiinnnnnn    nnnnnn      rrrrrrr               eeeeeeeeeeeeee j::::j   eeeeeeeeeeeeee     cccccccccccccccc         ttttttttttt     eeeeeeeeeeeeee    ddddddddd   ddddd         YYYYYYYYYYYYY   ooooooooooo      uuuuuuuu  uuuu\r\n" + 
	    		"                                                                                                                                          j::::j                                                                                                                                                          \r\n" + 
	    		"                                                                                                                                jjjj      j::::j                                                                                                                                                          \r\n" + 
	    		"                                                                                                                               j::::jj   j:::::j                                                                                                                                                          \r\n" + 
	    		"                                                                                                                               j::::::jjj::::::j                                                                                                                                                          \r\n" + 
	    		"                                                                                                                                jj::::::::::::j                                                                                                                                                           \r\n" + 
	    		"                                                                                                                                  jjj::::::jjj                                                                                                                                                            \r\n" + 
	    		"                                                                                                                                     jjjjjj                                                                                                                                                               \r\n" + 
	    		"");
	    
	    merchant.setVerified(false);
	    merchantService.updateMerchant(merchant);
	    
	    loginService.remove(merchant.getEmailId());
		
		
		return new ResponseEntity<List<Merchant>>(list_of_verified_merchants,HttpStatus.OK);
	}
	
//  *********************** Upload Images ****************************************
	
	List<String> files = new ArrayList<String>();
	
	@PostMapping("/post/{productId}")
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
	@PostMapping("/slider/{productId}/{Id}")
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
	
	
	
	@GetMapping("/viewProducts")
	public ResponseEntity<List<Product>> getAllProducts(){
		
		//System.out.println("VIEWproducts");
		List<Product> products=productService.getAllProducts();
		if(products.isEmpty())
			 return new ResponseEntity("Sorry ! Inventories not available!",HttpStatus.NOT_FOUND);
		
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
//	************************Inventory(Products)**********************************************
	
	
	@GetMapping("/editAllPromos/{promoCode}/{category}")
	public ResponseEntity<Boolean> editAllPromos(@PathVariable("promoCode") String promoCode,@PathVariable("category") String category){
		Promos promo=promoService.getPromo(promoCode);
		inventoryMerchantService.editAllPromos(promo,category);
		return new ResponseEntity(true,HttpStatus.OK);
			
	}

	@GetMapping("/viewInventories")
	public ResponseEntity<List<Inventory>> getInventoriesList(){
		
		
		List<Inventory> inventories=inventoryMerchantService.getInventoriesList();
		if(inventories.isEmpty())
			 return new ResponseEntity("Sorry ! Inventories not available!",HttpStatus.NOT_FOUND);
		
		
		return new ResponseEntity<List<Inventory>>(inventories,HttpStatus.OK);
		
	}
	
//	@PostMapping("/addInventory")
//	public ResponseEntity<List<Inventory>> addNewInventory(@RequestBody Inventory inventory){
//		
//		System.out.println(inventory);
//		List<Inventory> inventories=inventoryMerchantService.addNewInventory(inventory);
//		
//		if(inventories.isEmpty())
//			 return new ResponseEntity("Sorry!! Inventory List not available!",HttpStatus.NOT_FOUND);
//		
//		return new ResponseEntity<List<Inventory>>(inventories,HttpStatus.OK);
//		
//	}
//	
//	
	@DeleteMapping(value="/deleteInventory/{inventoryId}")
    public ResponseEntity<List<Inventory>>deleteInventory(@PathVariable("inventoryId")int inventoryId){
	
	   List<Inventory> inventories=inventoryMerchantService.deleteInventory(inventoryId);
	
	   if(inventories==null)
		  return new ResponseEntity("Sorry!! Inventory Id not available!",HttpStatus.NOT_FOUND);
	
	return new ResponseEntity<List<Inventory>>(inventories,HttpStatus.OK);
	
	}
	
	/*@PostMapping("/editInventories")
	public ResponseEntity<Boolean> editInventory(@RequestBody Inventory inventory){
		
		System.out.println(inventory);
				
		
		inventoryMerchantService.addNewInventory(inventory);
		productService.editProduct(inventory);
		List<Inventory> inventories=inventoryMerchantService.getAllInventories(merchant.getMerchantId());
		
		if(inventories.isEmpty())
			 return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		
	}*/
	
	
	@PostMapping("/inventory")
	public ResponseEntity<List<Inventory>>updateInventory(@RequestBody Inventory inventory){
		System.out.println("abds");
		List<Inventory> inventories=inventoryMerchantService.updateInventory(inventory);
		
		if(inventories==null)
			  return new ResponseEntity("",HttpStatus.OK);
		
		return new ResponseEntity<List<Inventory>>(inventories,HttpStatus.OK);
	
	}
	
	
//	************************Promotions**********************************************
	
	
	
	@GetMapping("/getAllPromos")
	public ResponseEntity<List<Promos>> getAllPromos(){
		
		List<Promos> promos=promoService.getAllPromos();
		if(promos.isEmpty())
			return new ResponseEntity("Sorry!! Promos not available",HttpStatus.NOT_FOUND);
		else return new ResponseEntity(promos,HttpStatus.OK);
			
	}
	
	@GetMapping("/getPromo/{promoCode}")
	public ResponseEntity<List<Promos>> getPromo(@PathVariable("promoCode") String promoCode){
		
		Promos promos=promoService.getPromo(promoCode);
		if(promos==null)
			return new ResponseEntity("Sorry!! Promos not available",HttpStatus.NOT_FOUND);
		else return new ResponseEntity(promos,HttpStatus.OK);
			
	}
	
	
	
	
	
//	************************Generate Coupons**********************************************
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	************************Generate Business Analysis**********************************************
	//for getting sales analysis(category wise)
	@GetMapping("/salesAnalysis/{fromDate}/to/{toDate}")
	public ResponseEntity<List<SalesAnalysis>> getSalesAnalysis(@PathVariable("fromDate") @DateTimeFormat(pattern="yyyy-MM-dd")
	Date fromDate, @PathVariable("toDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate)	{
		if(fromDate.before(toDate))	{
			List<SalesAnalysis> salesAnalysis=businessAnalysisService.getSalesAnalysis(fromDate, toDate);
			
			if(salesAnalysis.isEmpty())
				return new ResponseEntity("Sorry! No business during this time period!", HttpStatus.NOT_FOUND);
			return new ResponseEntity<List<SalesAnalysis>>(salesAnalysis,HttpStatus.OK);
		}
		return new ResponseEntity("Sorry! No business during this time period!", HttpStatus.NOT_FOUND);
	}
	
	//for getting dispatch details(for analyzing merchants)
	@GetMapping("/dispatchAnalysis/{fromDate}/to/{toDate}")
	public ResponseEntity<List<DispatchAnalysis>> getDispatchAnalysis(@PathVariable("fromDate") @DateTimeFormat(pattern="yyyy-MM-dd")
	Date fromDate, @PathVariable("toDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate)	{
		if(fromDate.before(toDate))	{
			List<DispatchAnalysis> dispatchAnalysis=businessAnalysisService.getDispatchDetailsBetween(fromDate, toDate);
			
			if(dispatchAnalysis.isEmpty())
				return new ResponseEntity("Sorry! No business during this time period!", HttpStatus.NOT_FOUND);
			return new ResponseEntity<List<DispatchAnalysis>>(dispatchAnalysis,HttpStatus.OK);
		}
		return new ResponseEntity("Sorry! No business during this time period!", HttpStatus.NOT_FOUND);
	}
	
	
	
	
	
	
}
