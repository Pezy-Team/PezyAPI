package com.pezy.pezy_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pezy.pezy_api.entity.ProductCategory;
import com.pezy.pezy_api.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {
	
	@Autowired
	private ProductCategoryRepository repository;
	
	public ProductCategory save(ProductCategory category) {
		return repository.save(category);
	}
	
	public List<ProductCategory> findAll() {
		return (List<ProductCategory>) repository.findAll();
	}
	
	public Optional<ProductCategory> findById(Long id) {
		return repository.findById(id);
	}

}
