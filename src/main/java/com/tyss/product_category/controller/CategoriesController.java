package com.tyss.product_category.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import com.tyss.product_category.exception.DepartmentNotFoundException;
import com.tyss.product_category.model.request.Categories;
import com.tyss.product_category.model.response.CategoryResponse;
import com.tyss.product_category.model.response.StandardResponse;
import com.tyss.product_category.service.PCMSServiceInf;

@RestController
@RequestMapping("/api/cate")
public class CategoriesController {
	
	private static final Logger log = LoggerFactory.getLogger(CategoriesController.class);
	
	@Autowired
	private PCMSServiceInf serviceInf;
	
	@GetMapping(path = "/checkCategory")
	public String checkCategory() {
		log.info("Health check end point for CATEGORIES CONTROLLER trigger succesfull..!");
		return "CATEGORIES CONTROLLER IS UP AND RUNNING..!";
	}
	
	@SuppressWarnings("unused")
	@PostMapping(path = "/addCategory")
	public ResponseEntity<CategoryResponse> addCategory(@RequestBody Categories categories) {
		try {
			log.info("addCategory end point starts.. with input request "+categories.toString());
			if(categories != null) {
				CategoryResponse addCategory = serviceInf.addCategory(categories);
				log.info("output from service layer for addCategory " + categories.toString());
				return new ResponseEntity<CategoryResponse>(addCategory, HttpStatus.OK);
			} else 
				log.debug("addCategory end point stop execution due to error in input" + categories.toString());
				throw new DepartmentNotFoundException("Please pass all the fields ");
		} catch (Exception e) {
			log.error("addCategory end point stop execution due to error " + e.getMessage());
			throw new InternalError("Error with execution due to "+e.getMessage());
		}
	}
	
	@GetMapping(path = "/getCategory")
	public ResponseEntity<CategoryResponse> getCategoryById(@RequestParam Integer categoryId){
		try {
			log.info("getCategoryById end point starts.. with input request "+categoryId);
			if(categoryId != null && categoryId != 0) {
				CategoryResponse byCategoryId = serviceInf.getByCategoryId(categoryId);
				log.info("output from service layer for getCategoryById " + categoryId);
				return new ResponseEntity<CategoryResponse>(byCategoryId, HttpStatus.OK);
			}else
				log.debug("getCategoryById end point stop execution due to error in input" + categoryId);
				throw new DepartmentNotFoundException("Pass the valid categoryId to fetch the details..!");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("getCategoryById end point stop execution due to error " + e.getMessage());
			throw new InternalError("Error due to "+e.getMessage());
		}
	}
	
	@SuppressWarnings("unused")
	@PatchMapping(path = "/updateCate")
	public ResponseEntity<CategoryResponse> updateCategory(@RequestBody Categories categories) {
		try {
			log.info("updateCategory end point starts.. with input request "+categories.toString());
			if(categories != null) {
				CategoryResponse addCategory = serviceInf.updateByCategoryId(categories);
				log.info("output from service layer for updateCategory " + categories.toString());
				return new ResponseEntity<CategoryResponse>(addCategory, HttpStatus.OK);
			} else 
				log.debug("updateCategory end point stop execution due to error in input" + categories.toString());
				throw new DepartmentNotFoundException("Please pass all the fields ");
		} catch (Exception e) {
			log.error("updateCategory end point stop execution due to error " + e.getMessage());
			throw new InternalError("Error with execution due to "+e.getMessage());
		}
	}
	
	@DeleteMapping(path = "/deleteCate/{categoryId}")
	public ResponseEntity<StandardResponse> deleteCategoryById(@PathVariable Integer categoryId){
		try {
			log.info("deleteCategoryById end point starts.. with input request "+categoryId);
			if(categoryId!= null && categoryId!=0) {
				Boolean deleteByCategoryId = serviceInf.deleteByCategoryId(categoryId);
				if(deleteByCategoryId) {					
					log.info("output from service layer for deleteCategoryById " + deleteByCategoryId);
					return new ResponseEntity<StandardResponse>(StandardResponse.success("Category details deleted successfully.!"), HttpStatus.OK);
				}
				else
					log.debug("deleteCategoryById end point stop execution due to error in input" + categoryId);
					throw new NotFoundException("Category details for give categoryId: "+ categoryId + " not found, Please pass the valid categoryId");
			}else
				log.debug("deleteCategoryById end point stop execution due to error in input" + categoryId);
				throw new DepartmentNotFoundException("Please pass the valid categoryId..!");
		} catch (Exception e) {
			log.error("deleteCategoryById end point stop execution due to error " + e.getMessage());
			throw new InternalError("Error while execution due to "+e.getMessage());
		}
	}

}
