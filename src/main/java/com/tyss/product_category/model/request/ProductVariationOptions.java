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
@Table(name = "product_variation_options")
public class ProductVariationOptions implements Serializable{
	
	private static final long serialVersionUID = 3875983824613654264L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_variation_options_id")
	private Integer productVariationOptionsId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_variations_id")
	private ProductVariations productVariations;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "option_id")
	private Options options;

}
