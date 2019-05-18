package com.capstore.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstore.model.Return;

@Repository("returnDao")
@Transactional
public interface IReturnDao extends JpaRepository<Return, Integer> {

}
