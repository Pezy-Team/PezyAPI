package com.pezy.pezy_api.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pezy.pezy_api.entity.Stores;
import com.pezy.pezy_api.entity.UserAddress;
import com.pezy.pezy_api.entity.StoreNearPostStation;
import com.pezy.pezy_api.helper.SessionFactoryHelper;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.repository.StoreNearPostStationRepository;
import com.pezy.pezy_api.repository.StoreRepository;

@Service
public class StoreNearPostStationService {
	
	@Autowired
	private StoreNearPostStationRepository repo;
	
	@Autowired
	private StoreRepository storeRepo;
	
	@Autowired(required = true)
	@PersistenceContext
	private EntityManager em;
	
	private ResponseMessage msg = new ResponseMessage();
	
	public ResponseEntity<?> create(StoreNearPostStation post) {
		post.setId(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(post));
	}
	
	public ResponseEntity<?> update(StoreNearPostStation post, Long id) {
		post.setId(id);
		return ResponseEntity.ok(repo.save(post));
	}
	
	public ResponseEntity<?> findById(Long id) {
		Optional<StoreNearPostStation> opt = repo.findById(id);
		
		if(opt.isPresent()) {
			return ResponseEntity.ok(opt);
		}
		msg.setMessage("Post station not found.");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
	
	public ResponseEntity<?> findAll(){
		List<StoreNearPostStation> posts = (List<StoreNearPostStation>) repo.findAll();
		
		if(!posts.isEmpty()) {
			return ResponseEntity.ok(posts);
		}
		msg.setMessage("Post station not found.");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
	
	public ResponseEntity<?> findByStoreId(Long id){
		msg.setMessage("Store not found");
		Optional<Stores> storeOptional = storeRepo.findById(id);
		if(storeOptional.isPresent()) {
			Stores store = storeOptional.get();
			if(!store.getPoststations().isEmpty()) {
				return ResponseEntity.ok(store.getPoststations());
			}
			msg.setMessage("Post station of this store not found.");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
	
	public ResponseEntity<?> deleteById(Long id){
		Stores store = repo.findById(id).get().getStore();
		store.getPoststations().removeIf(d -> d.getId() == id);
		storeRepo.save(store);
		msg.setMessage("Delete success.");
		return ResponseEntity.ok(msg);
	}

}
