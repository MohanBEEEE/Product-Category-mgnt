package com.tyss.product_category.model.request;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name =  "categories")
public class Categories implements Serializable{
	
	private static final long serialVersionUID = 62670862890201501L;

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name  = "category_id")
	private Integer categoryId;
	
	@Column(name  = "category_title")
	private String categoryTitle;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "departments_department_id")
	private Departments department;
	

}
