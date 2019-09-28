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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "res_store")
//@JsonIgnoreProperties(value = {"password", "token"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Store.class)
public class Store implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 5)
	private Long id;

	@Column(length = 50)
	private String name;

	@Column(length = 30)
	private String tel;

	@Column(length = 30)
	private String email;

	@Column(length = 255)
	private String address;

	@Column(length = 255)
	private String introduction;

	@Column(name = "video_introduction", length = 200)
	@JsonProperty("video_introduction")
	private String videoIntroduction;

	@Column(name = "id_card", length = 20)
	@JsonProperty("id_card")
	private String idCard;

	@Column(name = "id_card_with_real_face", length = 500)
	@JsonProperty("id_card_with_real_face")
	private String idCardWithRealFace;

	@Column(name = "account_name", length = 30)
	@JsonProperty("account_name")
	private String accountName;

	@Column(name = "bank_name", length = 30)
	@JsonProperty("bank_name")
	private String bankName;

	@Column(name = "account_no", length = 20)
	@JsonProperty("account_no")
	private String accountNo;

	@Column(name = "book_bank", length = 200)
	@JsonProperty("book_bank")
	private String bookBank;

	@Column(name = "payment_type", length = 500)
	@JsonProperty("payment_type")
	private String paymentType;
	
	@Column(name = "logo", length = 500)
	@JsonProperty("logo")
	private String logo;

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

}
