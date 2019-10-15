package com.pezy.pezy_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pezy.pezy_api.entity.Product;
import com.pezy.pezy_api.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public Product save(Product prod) {
		return repository.save(prod);
	}
	
	public List<Product> findAll() {
		return (List<Product>) repository.findAll();
	}
	
	public Optional<Product> findById(Long id) {
		return repository.findById(id);
	}
	
	public List<Product> findAllById(List<Long> ids) {
		return (List<Product>) repository.findAllById(ids);
	}
	
	public boolean deleteById(Long id) {
		repository.deleteById(id);
		return repository.existsById(id) ? false : true;
	}
	
}
