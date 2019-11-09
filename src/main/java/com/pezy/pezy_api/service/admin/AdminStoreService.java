package com.pezy.pezy_api.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pezy.pezy_api.entity.Stores;
import com.pezy.pezy_api.entity.User;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.pojo.UsersStore;
import com.pezy.pezy_api.repository.StoreRepository;

@Service
public class AdminStoreService {
	
	@Autowired
	private StoreRepository storeRepo;
	
	private ResponseMessage msg = new ResponseMessage();
	
	public ResponseEntity<?> findAll(Integer limit, Integer offset){
		List<Stores>  stores = storeRepo.findAll(PageRequest.of(offset, limit)).getContent();
		
		if(!stores.isEmpty()) {
			List<UsersStore> storeList = new ArrayList<UsersStore>();
			stores.forEach(store -> {
				UsersStore userStore = new UsersStore();
				User user = store.getUserStore();
				userStore.setUserId(user.getId());
				userStore.setName(user.getName());
				userStore.setEmail(user.getEmail());
				userStore.setUsername(user.getUsername());
				userStore.setTel(user.getTel());
				userStore.setStore(store);
				storeList.add(userStore);
			});
			
			return ResponseEntity.ok(storeList);
		}
		msg.setMessage("Store not found!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}

}
