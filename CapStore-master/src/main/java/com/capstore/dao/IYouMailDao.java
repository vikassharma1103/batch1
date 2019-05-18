package com.capstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capstore.model.Email;
import com.capstore.model.YouMailLogin;

@Repository("youMailDao")
@Transactional
public interface IYouMailDao extends JpaRepository<YouMailLogin	, Integer> {

	YouMailLogin getByEmailIdAndPassword(String emailId, String password);

	

}
