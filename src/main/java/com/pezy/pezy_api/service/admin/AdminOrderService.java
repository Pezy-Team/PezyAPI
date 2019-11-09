package com.pezy.pezy_api.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pezy.pezy_api.entity.Order;
import com.pezy.pezy_api.entity.User;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.pojo.UsersOrder;
import com.pezy.pezy_api.repository.OrderRepository;

@Service
public class AdminOrderService {
	
	@Autowired
	private OrderRepository repo;
	
	private ResponseMessage msg = new ResponseMessage();
	
	public ResponseEntity<?> findOrderWithPageable(Pageable pageable){
		List<Order> orderList = repo.findAllByOrderByOrderDateDesc(pageable);
		
		if(!orderList.isEmpty()) {
//			return ResponseEntity.ok(orderList);
			List<UsersOrder> usersOrderList = new ArrayList<UsersOrder>();
			orderList.forEach(order -> {
				UsersOrder users = new UsersOrder();
				User user = order.getUser();
				users.setUserId(user.getId());
				users.setName(user.getName());
				users.setEmail(user.getEmail());
				users.setTel(user.getTel());
				users.setUsername(user.getUsername());
				users.setOrder(order);
				usersOrderList.add(users);
			});
			return ResponseEntity.ok(usersOrderList);
			
		}
		msg.setMessage("Order not found.");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
	

}
