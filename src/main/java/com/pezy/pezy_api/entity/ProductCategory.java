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

import lombok.Data;

@Data
@Entity
@Table(name = "res_product_category")
//@JsonIgnoreProperties(value = {"password", "token"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ProductCategory.class)
public class ProductCategory implements Serializable {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 5)
	private Long id;

	private String name;
	
	@Column(name = "image_tile_menu", nullable = true)
	@JsonProperty(value = "image_tile_menu")
	private String imageTileMenu;
	
	@Enumerated(EnumType.ORDINAL)
	private BooleanEnum active;

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
	@Column(name = "categories", nullable = true)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER, mappedBy = "parent")
	@JsonManagedReference(value = "categories_parent")
	private List<ProductCategory> categories;
	
	@ManyToOne(targetEntity = ProductCategory.class)
	@JoinColumn(name = "category_id")
	@JsonBackReference(value = "categories_parent")
	private ProductCategory parent;
	
	@Column(name = "products", nullable = true)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "category")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference(value = "products_parent")
	private List<Product> products;
	
	
}
