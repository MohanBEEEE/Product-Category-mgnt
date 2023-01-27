package com.tyss.product_category.service;

import com.tyss.product_category.model.request.Categories;
import com.tyss.product_category.model.request.Departments;
import com.tyss.product_category.model.request.OptionTypes;
import com.tyss.product_category.model.request.Products;
import com.tyss.product_category.model.response.CategoryResponse;
import com.tyss.product_category.model.response.DepartmentResponse;
import com.tyss.product_category.model.response.OptionTypesResponse;
import com.tyss.product_category.model.response.ProductsResponse;

public interface PCMSServiceInf {
	
	//DEPARTMENT EP's
	public DepartmentResponse addDepart(Departments departments);
	public DepartmentResponse getDepartById(Integer deptId);
	public DepartmentResponse updateByDeptId(Departments departments);
	public Boolean deleteByDeptId(Integer deptId);
	
	// CATEGORIES EP's...
	public CategoryResponse addCategory(Categories categories);
	public CategoryResponse getByCategoryId(Integer categoryId);
	public CategoryResponse updateByCategoryId(Categories categories);
	public Boolean deleteByCategoryId(Integer categoryId);
	
	// PRODUCTS EP's...
	public ProductsResponse addProducts(Products products);
	public ProductsResponse getByProductId(Integer prodId);
	public ProductsResponse updateByProductId(Products products);
	public Boolean deleteByProductId(Integer prodId);
	
	//OPTIONTYPES EP's...
	public OptionTypesResponse addOptionTypes(OptionTypes optionTypes);
	public OptionTypesResponse getByOptionTypesId(Integer optiTypeId);
	public OptionTypesResponse updateByOptionTypesId(OptionTypes optionTypes);
	public Boolean deleteByOptionTypesId(Integer optiTypeId);

	//
}
