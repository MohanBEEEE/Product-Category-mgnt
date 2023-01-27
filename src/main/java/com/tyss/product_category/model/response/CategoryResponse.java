package com.tyss.product_category.model.response;

import java.io.Serializable;

import com.tyss.product_category.model.request.Categories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse implements Serializable {
	
	private static final long serialVersionUID = -519639516628814934L;
	
	private StandardResponse stdResp;
	private Categories data;
}
