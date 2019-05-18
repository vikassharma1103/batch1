package com.capstore.service;

import java.io.IOException;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.capstore.dao.IImageUploadDao;
import com.capstore.model.Product;
import com.capstore.model.ProductImage;
import com.gargoylesoftware.htmlunit.javascript.host.file.File;


@Service("storageService")
public class StorageService {

	
	@Autowired
	   IImageUploadDao uploadDao;
	   @Autowired
	  IProductService productService;
		//Logger log = LoggerFactory.getLogger(this.getClass().getName());
		

		private final Path rootLocation = Paths.get("C:\\Users\\poojha\\git\\CapStore\\src\\main\\resources\\static\\upload-dir");

		//private final Path rootLocation = Paths.get("C:\\Users\\bannapoo\\git\\CapStore1\\src\\main\\resources\\static\\upload-dir");

		//private final Path rootLocation = Paths.get("C:\\Users\\kalsuman\\git\\CapStore1\\src\\main\\resources\\static\\upload-dir");

		//private final Path rootLocation = Paths.get("C:\\Users\\mycharis\\git\\C\\apStores\\src\\main\\resources\\static\\upload-dir");
		
		ProductImage productImage=new ProductImage();
		Product  product=new Product();
		public void store(MultipartFile file,String productId) {
			product=productService.getProduct(Integer.parseInt(productId));
			
			try {
				productId=productId+".jpg";
				Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename().replace(file.getOriginalFilename(), productId)));
				
				//dbStore(productId);
				dbStore(this.rootLocation.resolve(file.getOriginalFilename().replace(file.getOriginalFilename(), productId)).toString());
			} catch (Exception e) {
				throw new RuntimeException("FAIL!");
			}
		}
		
		public void storeSlider(MultipartFile file,String productId,String Id) {
			product=productService.getProduct(Integer.parseInt(productId));
			
			try {
				productId=productId+"_"+Id+".jpg";
				Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename().replace(file.getOriginalFilename(), productId)));
				
				//dbStore(productId);
				dbStoreSlider(this.rootLocation.resolve(file.getOriginalFilename().replace(file.getOriginalFilename(), productId)).toString(),Id);
			} catch (Exception e) {
				throw new RuntimeException("FAIL!");
			}
		}
		
		public void dbStore(String string) {
			
			product.setImageUrl(string);
			productImage.setImageUrl(string);
			productImage.setImageStatus("main");
			productImage.setProduct(product);
			uploadDao.save(productImage);
			productService.updateProduct(product);
			
		}
		public void dbStoreSlider(String string,String Id) {
			ProductImage productImage=new ProductImage();
			productImage.setImageUrl(string);
			productImage.setImageStatus("slider"+Id);
			productImage.setProduct(product);
			uploadDao.save(productImage);
			
			
		}
		
		public Resource loadFile(String filename) {
			try {
				System.out.println("Check1: "+filename);
				String str=productImage.getImageUrl();
				int index=str.lastIndexOf("\\");
				str=str.substring(index+1, str.length());
				System.out.println(str);
			if(str.equals(filename))
			{
				
				Path file = rootLocation.resolve(filename);
				Resource resource = new UrlResource(file.toUri());
				System.out.println(resource);
				if (resource.exists() || resource.isReadable()) {
					return resource;
				} else {
					throw new RuntimeException("FAIL!");
				}
			}
			return null;
			} catch (MalformedURLException e) {
				throw new RuntimeException("FAIL!");
			}
		}


		public void init() {
			try {
				if(!Files.isDirectory(rootLocation))
				    Files.createDirectories(rootLocation);
			} catch (IOException e) {
				//throw new RuntimeException("Could not initialize storage!");
			}
		}
		
		
}
