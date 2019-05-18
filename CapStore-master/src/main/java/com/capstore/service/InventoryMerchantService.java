package com.capstore.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.dao.IInventoryMerchantDao;
import com.capstore.dao.IProductDao;
import com.capstore.model.Inventory;
import com.capstore.model.Promos;

@Service("inventoryMerchantService")
public class InventoryMerchantService implements IInventoryMerchantService{

	@Autowired
	private IInventoryMerchantDao inventoryMerchantDao;
	
	@Autowired
	private IProductService productService;
	
	@Override
	public List<Inventory> getAllInventories(int merchantId) {
		List<Inventory> inventories=inventoryMerchantDao.getAllInventoryByMerchantId(merchantId);
		
		return inventories;
	}

	@Override
	public List<Inventory> addNewInventory(Inventory inventory) {
		System.out.println("service"+inventory);
		
		inventoryMerchantDao.save(inventory);
		System.out.println("service after "+inventory);
		return null;
		
	}

	@Override
	public List<Inventory> deleteInventory(int inventoryId) {
		inventoryMerchantDao.deleteById(inventoryId);
		return null;
	}

	@Override
	public List<Inventory> updateInventory(Inventory inventory) {
		System.out.println(inventory);
		if(inventory.getStatus().equals("accepted")) {
			productService.editProduct(inventory);
			inventoryMerchantDao.save(inventory);	
		}else if(inventory.getStatus().equals("rejected")) {
			inventoryMerchantDao.delete(inventory);	
			productService.deleteProduct(inventory);
		}else if(inventory.getStatus().equals("pending")) {
			productService.editProduct(inventory);
			inventoryMerchantDao.save(inventory);			
		}
		return null;
	}

	@Override

	public List<Inventory> getInventoriesList() {
		
		return inventoryMerchantDao.findAll();
	}



	public void editAllPromos(Promos promo, String category) {
		List<Inventory> inventories=inventoryMerchantDao.findAll();
		int x=0;
		for(Inventory inventory:inventories) {
			if(inventory.getProductCategory().equals(category)) {
				inventories.get(x).setPromo(promo);
				inventoryMerchantDao.save(inventories.get(x));
			}
			x++;
		}
	}

	

}
