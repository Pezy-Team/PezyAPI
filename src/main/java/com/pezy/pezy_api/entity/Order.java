package com.pezy.pezy_api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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

import org.hibernate.annotations.Type;
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
import com.pezy.pezy_api.enumerate.OrderStatusEnum;

import lombok.Data;

@Data
@Entity
@Table(name = "store_order")
//@JsonIgnoreProperties(value = {"password", "token"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Order.class)
public class Order implements Serializable{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 5)
	private Long id;
	
	@JsonProperty(value = "sum_amount")
	@Column(name = "sum_amount")
	private Integer sumAmount;
	
	@JsonProperty(value = "order_no")
	@Column(name = "order_no")
	private String orderNo;
	
	@Column(name = "sum_price", precision = 10, scale = 2, columnDefinition = " Decimal(10,2) ")
	@JsonProperty(value = "sum_price")
	@Type(type = "big_decimal")
	private BigDecimal sumPrice;
	
	@JsonProperty(value = "order_date")
	@Column(name = "order_date")
	private Date orderDate = new Date();
	
	@JsonProperty(value = "payment_slips")
	@Column(name = "payment_slips")
	private String paymentSlips;
	
	@JsonProperty(value = "packing_picture")
	@Column(name = "packing_picture")
	private String packingPicture;
	
	@JsonProperty(value = "transport_bill_picture")
	@Column(name = "transport_bill_picture")
	private String transportBillPicture;
	
	@JsonProperty(value = "transport_no")
	@Column(name = "transport_no")
	private String transportNo;
	
	@Enumerated(EnumType.STRING)
	private OrderStatusEnum status = OrderStatusEnum.WAIT;

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
	@ManyToOne(targetEntity = User.class)
	@JsonBackReference(value = "user_order")
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "order")
	@JsonManagedReference(value = "order_orderline")
	@JsonProperty(value = "order_lines")
	@Column(name = "order_lines")
	private List<OrderLine> orderLines;

}
