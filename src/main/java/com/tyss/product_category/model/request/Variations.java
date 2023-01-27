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
@Table(name = "variations")
public class Variations implements Serializable {
	
	private static final long serialVersionUID = 272399807801192692L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "variation_id")
	private Integer variationId;
	
	@Column(name = "variation_title")
	private String variationTitle;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "variation_type_id")
	private VariationTypes variationTypes;

}
