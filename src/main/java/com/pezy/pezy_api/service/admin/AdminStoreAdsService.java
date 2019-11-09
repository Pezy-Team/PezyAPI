package com.pezy.pezy_api.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pezy.pezy_api.entity.StoreAds;
import com.pezy.pezy_api.enumerate.AdsProcessStatusEnum;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.repository.StoreAdsRepository;

@Service
public class AdminStoreAdsService {

	@Autowired
	private StoreAdsRepository repo;
	
	private ResponseMessage msg = new ResponseMessage();
	
	
	public ResponseEntity<?> findByOrderByCreateDateDesc(Integer limit, Integer offset){
		List<StoreAds> stores = repo.findByOrderByCreateDateDesc(PageRequest.of(offset, limit)).getContent();
		if(!stores.isEmpty()) {
			/**
			 * TODO : Attach store info.
			 */
			return ResponseEntity.ok(stores);
		}
		msg.setMessage("Ads banner not found!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
	
	public ResponseEntity<?> findByStatusOrderByCreateDateDesc(AdsProcessStatusEnum status, Integer limit, Integer offset){
		List<StoreAds> stores = repo.findByStatusOrderByCreateDateDesc(status, PageRequest.of(offset, limit))
				.getContent();
		
		if(!stores.isEmpty()) {
			/**
			 * TODO : Attach store info.
			 */
			return ResponseEntity.ok(stores);
		}
		msg.setMessage("Ads banner not found!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
}
