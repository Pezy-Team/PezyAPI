package com.pezy.pezy_api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pezy.pezy_api.enumerate.AdsProcessStatusEnum;

import lombok.Data;

@Data
@Entity
@Table(name = "res_store_ads")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = StoreAds.class)
public class StoreAds implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 5)
	private Long id;
	
	private String title;
	
	@Column(name = "price", precision = 10, scale = 2, columnDefinition = " Decimal(10,2) ")
	@Type(type = "big_decimal")
	private BigDecimal price;
	
	@Enumerated(EnumType.STRING)
	private AdsProcessStatusEnum status = AdsProcessStatusEnum.WAIT;
	
	@JsonProperty(value = "banner_ads")
	private String bannerAds;
	
	@JsonProperty(value = "running_date")
	@Column(name = "running_date", nullable = false)
	private Date runningDate;
	
	@JsonProperty(value = "expiry_date")
	@Column(name = "expiry_date", nullable = false)
	private Date expiryDate;
	
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
