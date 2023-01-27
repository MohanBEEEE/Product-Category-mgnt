package com.tyss.product_category.model.response;

import com.tyss.product_category.model.request.Departments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {
	private StandardResponse stdResp;
	private Departments data;
}
