package com.tyss.product_category.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class DepartmentNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 3136352994534226924L;
	
	private String msg;

}
