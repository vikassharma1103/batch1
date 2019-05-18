package com.capstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.dao.IProductImage;
import com.capstore.model.ProductImage;

@Service("productImageService")
public class ProductImageService implements IProductImageService{

	
	@Autowired
	IProductImage productImageDao;
	
	
	@Override
	public List<ProductImage> getimages() {
		// TODO Auto-generated method stub
		return productImageDao.findAll();
	}
	
}
