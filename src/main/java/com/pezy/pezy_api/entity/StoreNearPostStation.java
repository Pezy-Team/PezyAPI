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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pezy.pezy_api.enumerate.BooleanEnum;

import lombok.Data;

@Data
@Entity
@Table(name = "res_store_poststation")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = StoreNearPostStation.class)
public class StoreNearPostStation implements Serializable{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 5)
	private Long id;
	
	private String name;

	@Column(name = "latitude", precision = 4, scale = 15, columnDefinition = " Decimal(15, 15) ")
	@Type(type = "big_decimal")
	private BigDecimal latitude;
	
	@Column(name = "longitude", precision = 4, scale = 15, columnDefinition = " Decimal(15, 15) ")
	@Type(type = "big_decimal")
	private BigDecimal longitude;
	
	@Enumerated(EnumType.STRING)
	private BooleanEnum active = BooleanEnum.TRUE;

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
	@ManyToOne(targetEntity = Stores.class)
	@JoinColumn(name = "store_id")
	@JsonBackReference(value = "storePostStatusRef")
	private Stores store;
	

}
