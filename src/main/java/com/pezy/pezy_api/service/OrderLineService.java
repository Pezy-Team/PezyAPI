package com.pezy.pezy_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pezy.pezy_api.entity.OrderLine;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.repository.OrderLineRepository;

@Service
public class OrderLineService {
	
	@Autowired
	private OrderLineRepository repo;

	private ResponseMessage msg = new ResponseMessage();
	
	public ResponseEntity<?> create(OrderLine o){
		o.setId(null);
		return ResponseEntity.ok(repo.save(o));
	}
	
	public ResponseEntity<?> updateById(OrderLine o, Long id){
		o.setId(id);
		return ResponseEntity.ok(repo.save(o));
	}
	
	public ResponseEntity<?> findAll(){
		List<OrderLine> orders = (List<OrderLine>) repo.findAll();
		if(!orders.isEmpty()) {
			return ResponseEntity.ok(orders);
		}
		msg.setMessage("Order not found.");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
	
	public ResponseEntity<?> findById(Long id){
		Optional<OrderLine> orderOptional = repo.findById(id);
		if(orderOptional.isPresent()) {
			return ResponseEntity.ok(orderOptional);
		}
		msg.setMessage("Order with id " + id + " not found!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}

}
