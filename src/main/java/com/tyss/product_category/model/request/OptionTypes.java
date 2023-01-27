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
@Table(name = "option_types")
public class OptionTypes implements Serializable{
	
	private static final long serialVersionUID = 5850293245567230360L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "option_type_id")
	private Integer optionTypeId;
	
	@Column(name = "option_type_title")
	private String optionTypeTitle;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categories_category_id")
	private Categories categories;

}
