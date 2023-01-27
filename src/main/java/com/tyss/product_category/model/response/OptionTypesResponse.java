package com.tyss.product_category.model.response;

import com.tyss.product_category.model.request.OptionTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionTypesResponse {
	private StandardResponse standardResponse;
	private OptionTypes optionTypes;
}
