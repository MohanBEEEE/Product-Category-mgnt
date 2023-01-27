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
@Table(name = "options")
public class Options implements Serializable{
	
	private static final long serialVersionUID = -4598423515813836571L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "option_id")
	private Integer optionId;
	
	@Column(name = "option_title")
	private String optionTitle;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "option_type_id")
	private OptionTypes optionTypes;

}
