package com.pezy.pezy_api.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pezy.pezy_api.entity.User;
import com.pezy.pezy_api.enumerate.GenderEnum;
import com.pezy.pezy_api.enumerate.RegisterByDeviceEnum;
import com.pezy.pezy_api.enumerate.RegisterByEnum;
import com.pezy.pezy_api.enumerate.UserTypeEnum;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM res_user WHERE email = ?1")
	public List<User> findUserAuthenticateByEmail(String email);
	
	@Query(nativeQuery = true, value = "SELECT * FROM res_user WHERE username = ?1")
	public List<User> findUserAuthenticateByUsername(String username);
	
	@Query(nativeQuery = true, value = "SELECT * FROM res_user WHERE token = ?1")
	public User findUserByToken(String token);
	
	public List<User> findByTokenAndTokenExpireAfter(String token, Date now);
	
	public List<User> findByGenderIs(GenderEnum gender, Pageable page);
	
	public List<User> findByUserTypeIs(UserTypeEnum type, Pageable page);
	
	public List<User> findByRegisterDeviceIs(RegisterByDeviceEnum device, Pageable page);
	
	public List<User> findByRegisterByIs(RegisterByEnum regBy, Pageable page);
	
	public Optional<User> findByEmailIs(String email);
}
 