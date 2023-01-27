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
@Table(name = "variation_types")
public class VariationTypes implements Serializable{
	
	private static final long serialVersionUID = -6817605711688646686L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "variation_type_id")
	private Integer variationTypeId;
	
	@Column(name = "variation_type_title")
	private String variationTypeTitle;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categories_category_id")
	private Categories categories;

}
