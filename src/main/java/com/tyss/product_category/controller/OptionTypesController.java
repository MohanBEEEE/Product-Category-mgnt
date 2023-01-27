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
import com.tyss.product_category.model.request.OptionTypes;
import com.tyss.product_category.model.response.OptionTypesResponse;
import com.tyss.product_category.model.response.StandardResponse;
import com.tyss.product_category.service.PCMSServiceInf;

@RestController
@RequestMapping("/api/v1/optyp")
public class OptionTypesController {
	
	private static final Logger log = LoggerFactory.getLogger(OptionTypesController.class);
	
	@Autowired
	private PCMSServiceInf serviceInf;
	
	@GetMapping(path = "/checkOptionTypes")
	public String checkProduct() {
		log.info("Health check end point for OPTION TYPES CONTROLLER trigger succesfull..!");
		return "OPTION TYPES CONTROLLER IS UP AND RUNNING..!";
	}
	
	@PostMapping(path = "/addOptionTypes")
	public ResponseEntity<OptionTypesResponse> addOptionTypes(@RequestBody OptionTypes optionTypes) {
		try {
			log.info("addOptionTypes end point starts.. with input request "+optionTypes.toString());
			if(optionTypes != null) {
				OptionTypesResponse addOptionTypes = serviceInf.addOptionTypes(optionTypes);
				log.info("output from service layer for addOptionTypes " + addOptionTypes.toString());
				return new ResponseEntity<OptionTypesResponse>(addOptionTypes, HttpStatus.OK);
			} else 
				log.debug("addOptionTypes end point stop execution due to error in input" + optionTypes.toString());
				throw new DepartmentNotFoundException("Please pass all the fields ");
		} catch (Exception e) {
			log.error("addOptionTypes end point stop execution due to error " + e.getMessage());
			throw new InternalError("Error with execution due to "+e.getMessage());
		}
	}
	
	@GetMapping(path = "/getOptionTypes")
	public ResponseEntity<OptionTypesResponse> getOptionTypesById(@RequestParam Integer optionTypeId){
		try {
			log.info("getOptionTypesById end point starts.. with input request "+optionTypeId);
			if(optionTypeId != null && optionTypeId != 0) {
				OptionTypesResponse byOptionTypesId = serviceInf.getByOptionTypesId(optionTypeId);
				log.info("output from service layer for getOptionTypesById " + byOptionTypesId);
				return new ResponseEntity<OptionTypesResponse>(byOptionTypesId, HttpStatus.OK);
			}else
				log.debug("getOptionTypesById end point stop execution due to error in input" + optionTypeId);
				throw new DepartmentNotFoundException("Pass the valid optionalTypeId to fetch the details..!");
		} catch (Exception e) {
			log.error("getOptionTypesById end point stop execution due to error " + e.getMessage());
			throw new InternalError("Error due to "+e.getMessage());
		}
	}
	
	@PatchMapping(path = "/updateOptionTypes")
	public ResponseEntity<OptionTypesResponse> updateOptionTypes(@RequestBody OptionTypes optionTypes) {
		try {
			log.info("updateOptionTypes end point starts.. with input request "+optionTypes.toString());
			if(optionTypes != null) {
				OptionTypesResponse updateByOptionTypesId = serviceInf.updateByOptionTypesId(optionTypes);
				log.info("output from service layer for updateOptionTypes " + updateByOptionTypesId.toString());
				return new ResponseEntity<OptionTypesResponse>(updateByOptionTypesId, HttpStatus.OK);
			} else 
				log.debug("updateOptionTypes end point stop execution due to error in input" + optionTypes.toString());
				throw new DepartmentNotFoundException("Please pass all the fields ");
		} catch (Exception e) {
			log.error("updateOptionTypes end point stop execution due to error " + e.getMessage());
			throw new InternalError("Error with execution due to "+e.getMessage());
		}
	}
	
	@DeleteMapping(path = "/deleteOptionalTypes/{optionalTypeId}")
	public ResponseEntity<StandardResponse> deleteOptionalTypeById(@PathVariable Integer optionalTypeId){
		try {
			log.info("deleteOptionalTypeById end point starts.. with input request "+optionalTypeId);
			if(optionalTypeId!= null && optionalTypeId!=0) {
				Boolean deleteByOptionTypesId = serviceInf.deleteByOptionTypesId(optionalTypeId);
				if(deleteByOptionTypesId) {
					log.info("output from service layer for deleteByOptionTypesId " + deleteByOptionTypesId);
					return new ResponseEntity<StandardResponse>(StandardResponse.success("optionalType details deleted successfully.!"), HttpStatus.OK);
				}
				else
					log.debug("deleteOptionalTypeById end point stop execution due to error in input" + optionalTypeId);
					throw new NotFoundException("Product details for give categoryId: "+ optionalTypeId + " not found, Please pass the valid categoryId");
			}else
				log.debug("deleteOptionalTypeById end point stop execution due to error in input" + optionalTypeId);
				throw new DepartmentNotFoundException("Please pass the valid ProductId..!");
		} catch (Exception e) {
			log.error("deleteOptionalTypeById end point stop execution due to error " + e.getMessage());
			throw new InternalError("Error while execution due to "+e.getMessage());
		}
	}

}
