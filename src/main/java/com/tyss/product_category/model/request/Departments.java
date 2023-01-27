package com.tyss.product_category.model.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Departments implements Serializable{
	
	private static final long serialVersionUID = 2185579398895955617L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "department_id")
	private Integer departmentId;
	
	@Column(name = "department_title")
	@NotNull(message = "department title is required", groups = RequestValidator.class)
	@NotBlank(message = "department title is required", groups = RequestValidator.class)
	private String departmentTitle;


}
