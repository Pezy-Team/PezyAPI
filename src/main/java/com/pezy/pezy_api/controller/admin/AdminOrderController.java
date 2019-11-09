package com.pezy.pezy_api.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pezy.pezy_api.service.OrderService;
import com.pezy.pezy_api.service.admin.AdminOrderService;

@RestController
@RequestMapping("/v1/admin/order")
@CrossOrigin(origins = "*")
public class AdminOrderController {
	
	@Autowired
	private AdminOrderService adminOrderService;
	
	@GetMapping("/limit/{limit}/of-page/{offset}")
	public ResponseEntity<?> getTopForOrder(@PathVariable("limit")Integer limit, @PathVariable("offset")Integer offset){
		return adminOrderService.findOrderWithPageable(PageRequest.of(offset, limit));
	}
	
}
