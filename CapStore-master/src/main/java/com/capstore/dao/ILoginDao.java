package com.capstore.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstore.model.Customer;
import com.capstore.model.Login;

@Repository("loginDao")
@Transactional
public interface ILoginDao extends JpaRepository<Login,Integer>{
	/*@Query("from Login where emailId=:emailId and password=:password")*/
	public Login getByEmailIdAndPassword(String emailId, String password);

	public Login getByEmailId(String emailId);

	public void deleteByEmailId(String emailId);
	
}
