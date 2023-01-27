package com.tyss.product_category.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.tyss.product_category.model.request.Products;
import com.tyss.product_category.model.response.ProductsResponse;
import com.tyss.product_category.model.response.StandardResponse;
import com.tyss.product_category.service.PCMSServiceInf;

@RestController
@RequestMapping("/api/v1/product")
public class ProductsController {
	
	private static final Logger log = LoggerFactory.getLogger(ProductsController.class);
	
	@Autowired
	private PCMSServiceInf serviceInf;
	
	
	@GetMapping(path = "/checkProduct")
	public String checkProduct() {
		log.info("Health check end point for PRODUCTS CONTROLLER trigger succesfull..! ");
		return "PRODUCTS CONTROLLER IS UP AND RUNNING..! ";
	}
	
	@SuppressWarnings("unused")
	@PostMapping(path = "/addProducts")
	public ResponseEntity<ProductsResponse> addProducts(@RequestBody Products products) {
		try {
			log.info("addProducts end point starts.. with input request "+products.toString());
			if(products != null) {
				ProductsResponse addProducts = serviceInf.addProducts(products);
				log.info("output from service layer for addProducts " + products.toString());
				return new ResponseEntity<ProductsResponse>(addProducts, HttpStatus.OK);
			} else 
				log.debug("addProducts end point stop execution due to error in input" + products.toString());
				throw new DepartmentNotFoundException("Please pass all the fields ");
		} catch (Exception e) {
			log.error("addProducts end point stop execution due to error " + e.getMessage());
			throw new InternalError("Error with execution due to "+e.getMessage());
		}
	}
	
	@GetMapping(path = "/getProduct")
	public ResponseEntity<ProductsResponse> getProductsById(@RequestParam Integer productId){
		try {
			log.info("getProductsById end point starts.. with input request "+productId);
			if(productId != null && productId != 0) {
				ProductsResponse byProductId = serviceInf.getByProductId(productId);
				log.info("output from service layer for getProductsById " + byProductId);
				return new ResponseEntity<ProductsResponse>(byProductId, HttpStatus.OK);
			}else
				log.debug("getProductsById end point stop execution due to error in input" + productId);
				throw new DepartmentNotFoundException("Pass the valid productId to fetch the details..!");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("getProductsById end point stop execution due to error " + e.getMessage());
			throw new InternalError("Error due to "+e.getMessage());
		}
	}
	
	@SuppressWarnings("unused")
	@PatchMapping(path = "/updateProduct")
	public ResponseEntity<ProductsResponse> updateProduct(@RequestBody Products products) {
		try {
			log.info("updateProduct end point starts.. with input request "+products.toString());
			if(products != null) {
				ProductsResponse updateByProductId = serviceInf.updateByProductId(products);
				log.info("output from service layer for updateProduct " + updateByProductId.toString());
				return new ResponseEntity<ProductsResponse>(updateByProductId, HttpStatus.OK);
			} else 
				log.debug("updateProduct end point stop execution due to error in input" + products.toString());
				throw new DepartmentNotFoundException("Please pass all the fields ");
		} catch (Exception e) {
			log.error("updateProduct end point stop execution due to error " + e.getMessage());
			throw new InternalError("Error with execution due to "+e.getMessage());
		}
	}
	
	@DeleteMapping(path = "/deleteProduct/{productId}")
	public ResponseEntity<StandardResponse> deleteProductById(@PathVariable Integer productId){
		try {
			log.info("deleteProductById end point starts.. with input request "+productId);
			if(productId!= null && productId!=0) {
				Boolean deleteByProductId = serviceInf.deleteByProductId(productId);
				if(deleteByProductId) {		
					log.info("output from service layer for deleteProductById " + deleteByProductId);
					return new ResponseEntity<StandardResponse>(StandardResponse.success("Product details deleted successfully.!"), HttpStatus.OK);
				}
				else
					log.debug("deleteProductById end point stop execution due to error in input" + productId);
					throw new NotFoundException("Product details for give categoryId: "+ productId + " not found, Please pass the valid categoryId");
			}else
				log.debug("deleteProductById end point stop execution due to error in input" + productId);
				throw new DepartmentNotFoundException("Please pass the valid ProductId..!");
		} catch (Exception e) {
			log.error("deleteProductById end point stop execution due to error " + e.getMessage());
			throw new InternalError("Error while execution due to "+e.getMessage());
		}
	}
}
