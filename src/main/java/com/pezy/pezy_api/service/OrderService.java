package com.pezy.pezy_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pezy.pezy_api.entity.Order;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repo;
	
	private ResponseMessage msg = new ResponseMessage();
	
	public ResponseEntity<?> create(Order o){
		o.setId(null);
		return ResponseEntity.ok(repo.save(o));
	}
	
	public ResponseEntity<?> updateById(Order o, Long id){
		o.setId(id);
		return ResponseEntity.ok(repo.save(o));
	}
	
	public ResponseEntity<?> updateStatus(Long id, Order o){
		Optional<Order> orderOptional = repo.findById(id);
		if(orderOptional.isPresent()) {
			Order order = orderOptional.get();
			order.setStatus(o.getStatus());
			return ResponseEntity.ok(repo.save(order));
		}
		msg.setMessage("Order not found!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
	
	public ResponseEntity<?> findAll(){
		List<Order> orders = (List<Order>) repo.findAll();
		if(!orders.isEmpty()) {
			return ResponseEntity.ok(orders);
		}
		msg.setMessage("Order not found.");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
	
	public ResponseEntity<?> findById(Long id){
		Optional<Order> orderOptional = repo.findById(id);
		if(orderOptional.isPresent()) {
			return ResponseEntity.ok(orderOptional);
		}
		msg.setMessage("Order with id " + id + " not found!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
	
}
