package com.pezy.pezy_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pezy.pezy_api.entity.HelpCenter;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.repository.HelpCenterRepository;

@Service
public class HelpCenterService {
	
	@Autowired
	private HelpCenterRepository repo;
	
	private ResponseMessage msg = new ResponseMessage();
	
	public ResponseEntity<?> create(HelpCenter hc){
		hc.setId(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(hc));
	}
	
	
	public ResponseEntity<?> update(HelpCenter hc, Long id){
		hc.setId(id);
		return ResponseEntity.ok(repo.save(hc));
	}
	
	public ResponseEntity<?> findAll(){
		List<HelpCenter> hcs = (List<HelpCenter>) repo.findAll();
		if(!hcs.isEmpty()) {
			return ResponseEntity.ok(hcs);
		}
		msg.setMessage("Help center content not found!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
	
	public ResponseEntity<?> findById(Long id){
		Optional<HelpCenter> hcOptional = repo.findById(id);
		if(hcOptional.isPresent()) {
			return ResponseEntity.ok(hcOptional);
		}
		msg.setMessage("Help center content for id " + id + " not found!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
	
	public ResponseEntity<?> deleteById(Long id){
		repo.deleteById(id);
		if(repo.existsById(id)) {
			msg.setMessage("Can't delete this item!");
			return ResponseEntity.ok(msg);
		} else {
			msg.setMessage("Delete success.");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(msg);
		}
	}
	

}
