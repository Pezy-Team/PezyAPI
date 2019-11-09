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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pezy.pezy_api.enumerate.BooleanEnum;
import com.pezy.pezy_api.enumerate.StoreStatusEnum;

import lombok.Data;

@Data
@Entity
@Table(name = "res_store")
//@JsonIgnoreProperties(value = {"password", "token"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Stores.class)
public class Stores implements Serializable {

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

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StoreStatusEnum status = StoreStatusEnum.WAIT;
	
	@Column(name = "paid_date")
	private Date paidDate;
	
	@Column(name = "vdo_live_status")
	@Enumerated(EnumType.STRING)
	private BooleanEnum vdoLiveStatus = BooleanEnum.FALSE;
	
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
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id")
	@JsonBackReference(value = "user_store")
	@JsonProperty("user_store")
	private User userStore;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "store")
	@JsonManagedReference(value = "storePostStatusRef")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<StoreNearPostStation> poststations;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "store")
	@JsonManagedReference(value = "productStoreRef")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Product> products;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "store")
	@JsonManagedReference(value = "storeAdsRef")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<StoreAds> adsBanner;

}
