package com.pezy.pezy_api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import com.pezy.pezy_api.enumerate.BooleanEnum;

import lombok.Data;

@Data
@Entity
@Table(name = "help_center")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = HelpCenter.class)
public class HelpCenter implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 5)
	private Long id;
	
	private String title;
	
	@JsonProperty(value = "menu_message")
	@Column(name = "menu_message")
	private String menuMessage;

	@Column(name = "content", columnDefinition = " TEXT ")
	private String content;
	
	@JsonProperty(value = "info_graphic")
	@Column(name = "info_graphic")
	private String infoGraphic;
	
	private String banner;
	
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
}
