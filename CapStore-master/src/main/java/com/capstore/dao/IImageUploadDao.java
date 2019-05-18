package com.capstore.dao;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstore.model.ProductImage;



@Repository("uploadDao")
@Transactional
public interface IImageUploadDao extends JpaRepository<ProductImage, Integer> {

}
