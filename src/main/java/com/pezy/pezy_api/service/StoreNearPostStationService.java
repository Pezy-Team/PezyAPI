package com.pezy.pezy_api.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pezy.pezy_api.entity.Store;
import com.pezy.pezy_api.entity.StoreNearPostStation;
import com.pezy.pezy_api.helper.SessionFactoryHelper;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.repository.StoreNearPostStationRepository;
import com.pezy.pezy_api.repository.StoreRepository;

@Service
public class StoreNearPostStationService {
	
	private EntityManagerFactory emf;
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private StoreNearPostStationRepository repo;
	
	@Autowired
	private StoreRepository storeRepo;
	
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
		Optional<Store> storeOptional = storeRepo.findById(id);
		if(storeOptional.isPresent()) {
			Store store = storeOptional.get();
			if(!store.getPoststations().isEmpty()) {
				return ResponseEntity.ok(store.getPoststations());
			}
			msg.setMessage("Post station of this store not found.");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
	
	public ResponseEntity<?> deleteById(Long id){
		Long storeId = repo.findById(id).get().getId();
		
//		Session session = SessionFactoryHelper.getSessionFactory();
//		EntityManager em = session.getEntityManagerFactory().createEntityManager();
		emf = Persistence.createEntityManagerFactory("persistence");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		Store store = em.find(Store.class, storeId);
		boolean result = store.getPoststations().removeIf(p -> p.getId() == id);
		if(result) {
			em.getTransaction().commit();
			em.close();
			msg.setMessage("Delete successful.");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(msg);
		}
		em.getTransaction().rollback();
		em.close();
		msg.setMessage("Delete unsuccess.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
	}

}
