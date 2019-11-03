package com.pezy.pezy_api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import com.pezy.pezy_api.enumerate.OrderStatusEnum;

import lombok.Data;

@Data
@Entity
@Table(name = "order_line")
//@JsonIgnoreProperties(value = {"password", "token"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = OrderLine.class)
public class OrderLine implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 5)
	private Long id;
	
	private Integer amount;
	
	@Column(name = "price_before_vat", precision = 10, scale = 2, columnDefinition = " Decimal(10,2) ")
	@JsonProperty(value = "price_before_vat")
	@Type(type = "big_decimal")
	private BigDecimal priceBeforeVAT;
	
	@Column(name = "price_after_vat", precision = 10, scale = 2, columnDefinition = " Decimal(10,2) ")
	@JsonProperty(value = "price_after_vat")
	@Type(type = "big_decimal")
	private BigDecimal priceAfterVAT;
	

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
	 * Relations
	 */
	@ManyToOne(targetEntity = Product.class)
	@JoinColumn(name = "product_id")
	@JsonBackReference(value = "products_orderline")
	private Product product;
	
	@ManyToOne(targetEntity = Order.class)
	@JoinColumn(name = "order_id")
	@JsonBackReference(value = "order_orderline")
	private Order order;

}
