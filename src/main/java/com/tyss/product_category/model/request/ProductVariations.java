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
@Table(name = "product_variations")
public class ProductVariations implements Serializable{
	
	private static final long serialVersionUID = 9011497651426330998L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_variations_id")
	private Integer productVariationsId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private Products products;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variation_id")
	private Variations variations;

}
