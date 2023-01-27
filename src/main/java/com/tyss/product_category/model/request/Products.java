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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Products implements Serializable {
	
	private static final long serialVersionUID = -7625681798771767360L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name = "product_title")
	private String productTitle;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id")
	private Departments departments;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Categories categories;
	
	@ManyToOne(cascade =  CascadeType.ALL)
	@JoinColumn(name = "variation_type_id")
	private VariationTypes variationTypes;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "option_type_id")
	private OptionTypes optionTypes;
	

}
