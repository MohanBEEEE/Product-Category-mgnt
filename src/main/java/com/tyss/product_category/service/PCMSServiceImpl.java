package com.tyss.product_category.service;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.product_category.exception.DepartmentNotFoundException;
import com.tyss.product_category.model.request.Categories;
import com.tyss.product_category.model.request.Departments;
import com.tyss.product_category.model.request.OptionTypes;
import com.tyss.product_category.model.request.Products;
import com.tyss.product_category.model.response.CategoryResponse;
import com.tyss.product_category.model.response.DepartmentResponse;
import com.tyss.product_category.model.response.OptionTypesResponse;
import com.tyss.product_category.model.response.ProductsResponse;
import com.tyss.product_category.model.response.StandardResponse;
import com.tyss.product_category.repository.CategoryRepoInf;
import com.tyss.product_category.repository.DepartmentRepoInf;
import com.tyss.product_category.repository.OptionalTypeRepo;
import com.tyss.product_category.repository.ProductRepository;
@Service
public class PCMSServiceImpl implements PCMSServiceInf {

	private static final Logger log = LoggerFactory.getLogger(PCMSServiceImpl.class);
	
	@Autowired
	private DepartmentRepoInf deptReop;

	@Autowired
	private CategoryRepoInf categoriesRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	private OptionalTypeRepo optionalTypeRepo;

	// DEPARTMENTS EP's
	@Override
	public DepartmentResponse addDepart(Departments departments) {
		log.info("PCMSServiceImpl.addDepart starts..");
		Departments save = deptReop.save(departments);
		log.info("Response from department repository for addDepartment is "+save.toString());
		if (save != null)
			return new DepartmentResponse(StandardResponse.success("Department Details saved successfully..!! "), save);
		else
			log.error("Error in PCMSServiceImpl.addDepartment and failed to save..!");
			throw new InternalError("Department Details failed to save.!!");
	}

	@Override
	public DepartmentResponse getDepartById(Integer deptId) {
		log.info("PCMSServiceImpl.getDepartById starts..");
		Departments findById = deptReop.findById(deptId).orElseThrow(() -> new DepartmentNotFoundException(
				"Give Department Id " + deptId + " is not available, Please pass the valid department number"));
		log.info("Response from department repository for getDepartById is "+findById.toString());
			return new DepartmentResponse(StandardResponse.success("Department details fetch successful.!"),
					findById);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DepartmentResponse updateByDeptId(Departments departments) {
		Integer departmentId = departments.getDepartmentId();
		try {
			log.info("PCMSServiceImpl.updateByDeptId starts..");
			Departments findById = deptReop.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException("No details exist with deptId " + departmentId));
			log.info("Response from department repository for updateByDeptId is "+findById.toString());
				BeanUtils.copyProperties(departments, findById);
				departments = deptReop.save(findById);
				return new DepartmentResponse(StandardResponse.success("Department details Updated successful.!"),
						departments);
		} catch (NoSuchElementException e) {
			log.error("Error occured during updateByDeptId with exception "+e.getMessage());
			throw new NoSuchElementException("Data Not found..! Please add the Department first.");
		} catch (Exception e) {
			log.error("Error occured during updateByDeptId with exception "+e.getMessage());
			throw new InternalError("Error while execution due to " + e.getMessage());
		}
	}

	@Override
	public Boolean deleteByDeptId(Integer deptId) {
		// check if exist or not in DB then go for Delete option..
		log.info("PCMSServiceImpl.deleteByDeptId starts..");
		Boolean isDelete = false;
		if (isDelete != true) {
			deptReop.deleteById(deptId);
			log.info("Department with deptId: "+deptId+ " deleted successfully..!");
			isDelete = true;
		}
		return isDelete;
	}

	// CATEGORIES EP's...
	@Override
	public CategoryResponse addCategory(Categories categories) {
		try {
			log.info("PCMSServiceImpl.addCategory starts..");
			Categories save = categoriesRepo.save(categories);
			log.info("Response from categories repo for PCMSServiceImpl.addCategory is "+save.toString());
			if (save != null)
				return new CategoryResponse(StandardResponse.success("Categories details add successfully..!"), save);
			else
				log.debug("Response from categories repo for PCMSServiceImpl.addCategory is "+save.toString());
				throw new DepartmentNotFoundException("categories details failed to add..!");
		} catch (Exception e) {
			log.error("Error in PCMSServiceImpl.addCategory and failed to save..!"+e.getMessage());
			throw new InternalError("Error while exicution due to " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public CategoryResponse getByCategoryId(Integer categoryId) {
		try {
			log.info("PCMSServiceImpl.getByCategoryId starts..");
			Categories orElseThrow = categoriesRepo.findById(categoryId)
					.orElseThrow(() ->new DepartmentNotFoundException("No category exist with categoryId: "+categoryId + " please pass the valid categoryId..!"));
			log.info("Response from categories repository for getByCategoryId is "+orElseThrow.toString());
			return new CategoryResponse(StandardResponse.success("Category fetch successfull..!"), orElseThrow);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error in PCMSServiceImpl.getByCategoryId and failed to fetch..!"+e.getMessage());
			throw new InternalError("Error while execution due to "+e.getMessage());
		}
	}

	@Override
	public CategoryResponse updateByCategoryId(Categories categories) {
		try {
			log.info("PCMSServiceImpl.updateByCategoryId starts..");
			Categories findById = categoriesRepo.findById(categories.getCategoryId()).orElseThrow(() -> new NullPointerException("Category with categryId: "+categories.getCategoryId()+" is not valid to update it..!"));
			log.info("Response from categories repository for updateByCategoryId to check weather the id is present or not is "+findById);
			if(findById != null) {
				BeanUtils.copyProperties(categories, findById);
				Categories save = categoriesRepo.save(findById);
				log.info("Response from categories repository for updateByCategoryId is "+save.toString());
				return new CategoryResponse(StandardResponse.success("Category details updated succssfully..!"), save);
			}else
				log.debug("failed to updated the category given "+categories.toString());
				throw new DepartmentNotFoundException("Please pass the valid category details to update it..!");
		} catch (Exception e) {
			log.error("Error in PCMSServiceImpl.updateByCategoryId and failed to update..! with "+e.getMessage());
			throw new InternalError("Error while execution due to "+e.getMessage());
		}
	}

	@Override
	public Boolean deleteByCategoryId(Integer categoryId) {
		log.info("PCMSServiceImpl.deleteByCategoryId starts..");
		Boolean isDelete = false;
		Categories findById = categoriesRepo.findById(categoryId).orElseThrow(() -> new NullPointerException("No category exist with categoryId: "+categoryId + " please pass the valid categoryId..!"));
		log.info("Response from categories repository for deleteByCategoryId is "+findById);
		if (findById != null && isDelete!= true) {
			categoriesRepo.deleteById(categoryId);
			log.info("category has been deleted from database succesfully..!");
			isDelete = true;
		}
		return isDelete;
	}
	
	// PRODUCTS EP's...
	@Override
	public ProductsResponse addProducts(Products products) {
		try {
			log.info("PCMSServiceImpl.addProducts starts..");
			Products save = productRepo.save(products);
			log.info("Response from products repo for PCMSServiceImpl.addProducts is "+save.toString());
			if (save != null)
				return new ProductsResponse(StandardResponse.success("Products details add successfully..!"), save);
			else
				log.debug("Response from products repo for PCMSServiceImpl.addProducts is "+save.toString());
				throw new DepartmentNotFoundException("products details failed to add..!");
		} catch (Exception e) {
			log.error("Error in PCMSServiceImpl.addProducts and failed to save..!"+e.getMessage());
			throw new InternalError("Error while exicution due to " + e.getMessage());
		}
	}

	@Override
	public ProductsResponse getByProductId(Integer prodId) {
		try {
			log.info("PCMSServiceImpl.getByProductId starts..");
			Products orElseThrow = productRepo.findById(prodId)
					.orElseThrow(() ->new DepartmentNotFoundException("No products exist with productId: "+prodId + " please pass the valid productId..!"));
			log.info("Response from products repository for getByProductId is "+orElseThrow.toString());
			return new ProductsResponse(StandardResponse.success("product fetch successfull..!"), orElseThrow);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error in PCMSServiceImpl.getByProductId and failed to fetch..!"+e.getMessage());
			throw new InternalError("Error while execution due to "+e.getMessage());
		}
	}

	@Override
	public ProductsResponse updateByProductId(Products products) {
		try {
			log.info("PCMSServiceImpl.updateByProductId starts..");
			Products orElseThrow = productRepo.findById(products.getProductId()).orElseThrow(() -> new NullPointerException("Products with productId: "+products.getProductId()+" is not valid to update it..!"));
			log.info("Response from products repository for updateByProductId to check weather the id is present or not is "+orElseThrow.toString());
			if(orElseThrow != null) {
				BeanUtils.copyProperties(orElseThrow, products);
				Products save = productRepo.save(products);
				log.info("Response from products repository for updateByProductId is "+save.toString());
				return new ProductsResponse(StandardResponse.success("product details updated succssfully..!"), save);
			}else
				log.debug("failed to updated the product details given "+products.toString());
				throw new DepartmentNotFoundException("Please pass the valid product details to update it..!");
		} catch (Exception e) {
			log.error("Error in PCMSServiceImpl.updateByProductId and failed to update..! with "+e.getMessage());
			throw new InternalError("Error while execution due to "+e.getMessage());
		}
	}

	@Override
	public Boolean deleteByProductId(Integer prodId) {
		// TODO Auto-generated method stub
		log.info("PCMSServiceImpl.deleteByProductId starts..");
		Boolean isDelete = false;
		Products orElseThrow = productRepo.findById(prodId).orElseThrow(() -> new NullPointerException("No products exist with productId: "+prodId + " please pass the valid productId..!"));
		log.info("Response from product repository for deleteByProductId is "+orElseThrow.toString());
		if (orElseThrow != null && isDelete!= true) {
			productRepo.deleteById(prodId);
			log.info("product has been deleted from database succesfully..!");
			isDelete = true;
		}
		return isDelete;
	}

	// OPTIONTYPES EP's...
	@Override
	public OptionTypesResponse addOptionTypes(OptionTypes optionTypes) {
//		 TODO Auto-generated method stub
		try {
			log.info("PCMSServiceImpl.addOptionTypes starts..");
			OptionTypes save = optionalTypeRepo.save(optionTypes);
			log.info("Response from optional type repo for PCMSServiceImpl.addOptionTypes is "+save.toString());
			if (save != null)
				return new OptionTypesResponse(StandardResponse.success("optional type details add successfully..!"), save);
			else
				log.debug("Response from option type repo for PCMSServiceImpl.addOptionTypes is "+save.toString());
				throw new DepartmentNotFoundException("option type details failed to add..!");
		} catch (Exception e) {
			log.error("Error in PCMSServiceImpl.addOptionTypes and failed to save..!"+e.getMessage());
			throw new InternalError("Error while exicution due to " + e.getMessage());
		}
	}

	@Override
	public OptionTypesResponse getByOptionTypesId(Integer optiTypeId) {
		// TODO Auto-generated method stub
		try {
			log.info("PCMSServiceImpl.getByOptionTypesId starts..");
			OptionTypes orElseThrow = optionalTypeRepo.findById(optiTypeId)
					.orElseThrow(() ->new DepartmentNotFoundException("No products exist with productId: "+optiTypeId + " please pass the valid productId..!"));
			log.info("Response from optional type repository for getByOptionaTypeId is "+orElseThrow.toString());
			return new OptionTypesResponse(StandardResponse.success("option type fetch successfull..!"), orElseThrow);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error in PCMSServiceImpl.getByOptionTypesId and failed to fetch..!"+e.getMessage());
			throw new InternalError("Error while execution due to "+e.getMessage());
		}
	}

	@Override
	public OptionTypesResponse updateByOptionTypesId(OptionTypes optionTypes) {
		// TODO Auto-generated method stub
		try {
			log.info("PCMSServiceImpl.updateByOptionTypesId starts..");
			OptionTypes orElseThrow = optionalTypeRepo.findById(optionTypes.getOptionTypeId()).orElseThrow(() -> new NullPointerException("Products with productId: "+optionTypes.getOptionTypeTitle()+" is not valid to update it..!"));
			log.info("Response from products repository for updateByProductId to check weather the id is present or not is "+orElseThrow.toString());
			if(orElseThrow != null) {
				BeanUtils.copyProperties(orElseThrow, optionTypes);
				OptionTypes save = optionalTypeRepo.save(optionTypes);
				log.info("Response from option type repository for updateByOptionTypesId is "+save.toString());
				return new OptionTypesResponse(StandardResponse.success("option type details updated succssfully..!"), save);
			}else
				log.debug("failed to updated the option type details given "+optionTypes.toString());
				throw new DepartmentNotFoundException("Please pass the valid option type details to update it..!");
		} catch (Exception e) {
			log.error("Error in PCMSServiceImpl.updateByOptionTypesId and failed to update..! with "+e.getMessage());
			throw new InternalError("Error while execution due to "+e.getMessage());
		}
	}

	@Override
	public Boolean deleteByOptionTypesId(Integer optiTypeId) {
		// TODO Auto-generated method stub
		log.info("PCMSServiceImpl.deleteByOptionTypesId starts..");
		Boolean isDelete = false;
		OptionTypes orElseThrow = optionalTypeRepo.findById(optiTypeId).orElseThrow(() -> new NullPointerException("No products exist with productId: "+optiTypeId + " please pass the valid productId..!"));
		log.info("Response from option type repository for deleteByOptionTypesId is "+orElseThrow.toString());
		if (orElseThrow != null && isDelete!= true) {
			optionalTypeRepo.deleteById(optiTypeId);
			log.info("option type has been deleted from database succesfully..!");
			isDelete = true;
		}
		return isDelete;
	}
	

}
