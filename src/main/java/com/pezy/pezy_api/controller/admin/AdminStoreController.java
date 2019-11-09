package com.pezy.pezy_api.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pezy.pezy_api.entity.StoreAds;
import com.pezy.pezy_api.pojo.SearchStoreParam;
import com.pezy.pezy_api.service.admin.AdminStoreAdsService;
import com.pezy.pezy_api.service.admin.AdminStoreService;

@RestController
@RequestMapping("/v1/admin/store")
@CrossOrigin(origins = "*")
public class AdminStoreController {

	@Autowired
	private AdminStoreService service;
	
	@Autowired
	private AdminStoreAdsService adsService;
	
	@GetMapping("/limit/{limit}/of-page/{offset}")
	public ResponseEntity<?> findAll(@PathVariable("limit")Integer limit, @PathVariable("offset")Integer offset){
		return service.findAll(limit, offset);
	}
	
	
	@PostMapping("/limit/{limit}/of-page/{offset}")
	public ResponseEntity<?> findByPaidDateBetweenAndStatus(@PathVariable("limit")Integer limit, 
			@PathVariable("offset")Integer offset, @RequestBody SearchStoreParam param){
		return service.findByPaidDateBetweenAndStatus(param, limit, offset);
	}
	
	@GetMapping("/ads/limit/{limit}/of-page/{offset}")
	public ResponseEntity<?> findByOrderByCreateDateDesc(@PathVariable("limit")Integer limit, @PathVariable("offset")Integer offset){
		return adsService.findByOrderByCreateDateDesc(limit, offset);
	}
	
	@PostMapping("/ads/limit/{limit}/of-page/{offset}")
	public ResponseEntity<?> findByStatusOrderByCreateDateDesc(@PathVariable("limit")Integer limit, @PathVariable("offset")Integer offset,
			@RequestBody StoreAds ads){
		return adsService.findByStatusOrderByCreateDateDesc(ads.getStatus(), limit, offset);
	}
	
}
