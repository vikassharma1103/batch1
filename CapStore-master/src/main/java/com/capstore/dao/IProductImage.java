package com.capstore.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstore.model.ProductImage;

@Repository("productImageDao")
@Transactional
public interface IProductImage extends JpaRepository<ProductImage, Integer> {

}
