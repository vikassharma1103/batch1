package com.capstore.service;

import java.util.List;

import com.capstore.model.Inventory;
import com.capstore.model.Promos;

public interface IInventoryMerchantService {

	
	
	public List<Inventory> getAllInventories(int merchantId);

    public List<Inventory> addNewInventory(Inventory inventory);

	public List<Inventory> deleteInventory(int inventoryId);

	public List<Inventory> updateInventory(Inventory inventory);


	public List<Inventory> getInventoriesList();



	public void editAllPromos(Promos promo, String category);

	

}
