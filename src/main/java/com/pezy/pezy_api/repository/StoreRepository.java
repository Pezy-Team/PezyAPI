package com.pezy.pezy_api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pezy.pezy_api.entity.Stores;
import com.pezy.pezy_api.enumerate.StoreStatusEnum;

@Repository
public interface StoreRepository extends CrudRepository<Stores, Long>{
	
	public Page<Stores> findAll(Pageable pageable);
	
	public Page<Stores> findByPaidDateBetweenAndStatus(Date from , Date until, StoreStatusEnum status, Pageable pageable);

}
