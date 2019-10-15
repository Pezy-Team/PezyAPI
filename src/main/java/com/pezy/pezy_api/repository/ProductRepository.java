package com.pezy.pezy_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pezy.pezy_api.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	
}
