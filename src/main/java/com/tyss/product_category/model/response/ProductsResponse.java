package com.tyss.product_category.model.response;

import java.io.Serializable;

import com.tyss.product_category.model.request.Products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsResponse implements Serializable {
	
	private static final long serialVersionUID = 2469574299230607984L;
	
	private StandardResponse standardResponse;
	private Products products;

}
