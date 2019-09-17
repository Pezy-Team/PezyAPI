package com.pezy.pezy_api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "res_user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = User.class)
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 5)
	private Long id;
	
	@Column(length = 60)
	private String name;
	
	@Column(length = 20)
	private String tel;
	
	@Column(length = 30)
	private String email;
	
	@Column(length = 25)
	private String username;
	
	@Column(length = 50)
	private String password;
	
	@CreatedBy
	@Column(name = "create_uid")
	private Long createUID;
	
	@LastModifiedBy
	@Column(name = "update_uid")
	private Long updateUID;
	
	@CreatedDate
	@Column(name = "create_date")
	private Date createDate = new Date();
	
	@LastModifiedDate
	@Column(name = "update_date")
	private Date updateDate = new Date();
}
