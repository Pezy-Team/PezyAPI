package com.pezy.pezy_api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pezy.pezy_api.enumerate.GenderEnum;
import com.pezy.pezy_api.enumerate.UserTypeEnum;

import lombok.Data;

@Data
@Entity
@Table(name = "res_user")
@JsonIgnoreProperties(value = {"password", "token"}, allowSetters = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = User.class)
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 5)
	private Long id;

	@Column(length = 60)
	private String name;

	@Column(length = 20)
	private String tel;

	@Column(length = 50)
	private String email;

	@Column(length = 25)
	private String username;

	@Column(length = 500)
	private String password;

	@Column(length = 500)
	private String token;
	
	@Column(name = "profile_image", length = 500)
	@JsonProperty("profile_image")
	private String profileImage;

	@Column(name = "user_type")
	@JsonProperty("user_type")
	@Enumerated(EnumType.ORDINAL)
	private UserTypeEnum userType = UserTypeEnum.CUSTOMER;

	@Column(name = "gender")
	@Enumerated(EnumType.ORDINAL)
	private GenderEnum gender = GenderEnum.MALE;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "token_expire")
	@JsonProperty("token_expire")
	private Date tokenExpire;

	@CreatedBy
	@Column(name = "create_uid")
	@JsonProperty("create_uid")
	private Long createUID;

	@LastModifiedBy
	@Column(name = "update_uid")
	@JsonProperty("update_uid")
	private Long updateUID;

	@CreatedDate
	@Column(name = "create_date")
	@JsonProperty("create_date")
	private Date createDate = new Date();

	@LastModifiedDate
	@Column(name = "update_date")
	@JsonProperty("update_date")
	private Date updateDate = new Date();

	/**
	 * Relation
	 */
	@Column(name = "addresses")
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userAddress")
	@JsonManagedReference(value = "user_address")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<UserAddress> addresses;
	
	@Column(name = "stores")
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userStore")
	@JsonManagedReference(value = "user_store")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Store> stores;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "user")
	@JsonManagedReference(value = "user_order")
	private Set<Order> orders;

}
