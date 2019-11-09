package com.pezy.pezy_api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pezy.pezy_api.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{
	
	public Page<Order> findAllByOrderByOrderDateDesc(Pageable page);

}
