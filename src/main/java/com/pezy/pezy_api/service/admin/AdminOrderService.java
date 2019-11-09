package com.pezy.pezy_api.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pezy.pezy_api.entity.Order;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.repository.OrderRepository;

@Service
public class AdminOrderService {
	
	@Autowired
	private OrderRepository repo;
	
	private ResponseMessage msg = new ResponseMessage();
	
	public ResponseEntity<?> findOrderWithPageable(Pageable pageable){
		List<Order> orderList = repo.findAllByOrderByOrderDateDesc(pageable);
		
		if(!orderList.isEmpty()) {
			return ResponseEntity.ok(orderList);
		}
		msg.setMessage("Order not found.");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
	

}
