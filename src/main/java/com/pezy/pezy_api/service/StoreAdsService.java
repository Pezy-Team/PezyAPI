package com.pezy.pezy_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pezy.pezy_api.entity.Stores;
import com.pezy.pezy_api.entity.StoreAds;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.repository.StoreAdsRepository;
import com.pezy.pezy_api.repository.StoreRepository;

@Service
public class StoreAdsService {

	@Autowired
	private StoreAdsRepository repo;
	
	@Autowired
	private StoreRepository storeRepo;
	
	private ResponseMessage msg = new ResponseMessage();
	
	public ResponseEntity<?> create(StoreAds ads) {
		ads.setId(null);
		return ResponseEntity.ok(repo.save(ads));
	}
	
	
	public ResponseEntity<?> update(StoreAds ads){
		return ResponseEntity.ok(repo.save(ads));
	}
	
	public ResponseEntity<?> findById(Long id){
		msg.setMessage("Ads banner not found.");
		Optional<StoreAds> adsOpt = repo.findById(id);
		if(adsOpt.isPresent()) {
			return ResponseEntity.ok(adsOpt);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
	
	public ResponseEntity<?> findAll(){
		msg.setMessage("Ads banner not found.");
		List<StoreAds> ads = (List<StoreAds>) repo.findAll();
		if(!ads.isEmpty()) {
			return ResponseEntity.ok(ads);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
	
	public ResponseEntity<?> findAdsByStoreId(Long storeId){
		msg.setMessage("Store not found");
		Optional<Stores> storeOpt = storeRepo.findById(storeId);
		if(storeOpt.isPresent()) {
			if(!storeOpt.get().getAdsBanner().isEmpty()) {
				return ResponseEntity.ok(storeOpt.get().getAdsBanner());
			}
			msg.setMessage("Store's ads banner not found!");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
	
	
}
