package com.capstore;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.capstore.service.StorageService;




@SpringBootApplication
@ComponentScan(basePackages= {"com.capstore"})
public class CapStoreApplication implements CommandLineRunner{

	
	@Resource
	StorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(CapStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		

		
		storageService.init();
		
	}
}
