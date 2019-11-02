package com.pezy.pezy_api.entity;

import java.awt.datatransfer.StringSelection;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Table;
import javax.print.attribute.IntegerSyntax;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.jgit.annotations.Nullable;
import org.hibernate.annotations.Type;
import org.springframework.core.io.Resource;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pezy.pezy_api.enumerate.BooleanEnum;
import com.pezy.pezy_api.pojo.UploadFileResponse;
import com.pezy.pezy_api.utils.FileUploadUtils;

import lombok.Data;
import lombok.val;

@Data
@Entity
@Table(name = "res_product")
//@JsonIgnoreProperties(value = {"password", "token"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Product.class)
public class Product implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 5)
	private Long id;
	
	private String name;
	
	private String colour;
	
	private String size;
	
	private String capacity;
	
	@Column(name = "price", precision = 10, scale = 2, columnDefinition = " Decimal(10,2) ")
	@Type(type = "big_decimal")
	private BigDecimal price;
	
	@Enumerated(EnumType.ORDINAL)
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
	 * Relations
	 */


	@ManyToOne(targetEntity = ProductCategory.class)
	@JoinColumn(name = "category_id", nullable = false)
	@JsonBackReference(value = "products_parent")
	private ProductCategory category;

	@ManyToOne(targetEntity = Store.class)
	@JoinColumn(name = "store_id")
	@JsonBackReference(value = "productStoreRef")
	private Store store;

}
