package com.pezy.pezy_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pezy.pezy_api.entity.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM res_user WHERE email = ?1")
	public List<User> findUserAuthenticateByEmail(String email);
	
	@Query(nativeQuery = true, value = "SELECT * FROM res_user WHERE username = ?1")
	public List<User> findUserAuthenticateByUsername(String username);
	
}
