package com.tyss.product_category.controller;

import javax.annotation.security.RolesAllowed;

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

import com.tyss.product_category.exception.DepartmentNotFoundException;
import com.tyss.product_category.model.request.Departments;
import com.tyss.product_category.model.response.DepartmentResponse;
import com.tyss.product_category.model.response.StandardResponse;
import com.tyss.product_category.service.PCMSServiceInf;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/dept")
public class DepartmentController {

	@Autowired
	private PCMSServiceInf serviceInf;

	@GetMapping(path = "/checkDept")
	@RolesAllowed("*")
	public String health() {
		log.info("Health check end point for DEPARTMENT CONTROLLER trigger succesfull..!");
		return "DEPARTMENT CONTROLLER IS UP AND RUNNING..!!";
	}

	@SuppressWarnings("unused")
	@PostMapping(path = "/addDept")
	public ResponseEntity<DepartmentResponse> addDept(@RequestBody Departments departments) {
		try {
			log.info("Add department end point starts.. with input request " + departments.toString());
			DepartmentResponse deptResponse = null;
			if (departments != null) {
				deptResponse = serviceInf.addDepart(departments);
				log.info("output from service layer for add dept " + deptResponse.toString());
				return new ResponseEntity<DepartmentResponse>(deptResponse, HttpStatus.OK);
			} else
				log.debug("Add department end point stop execution due to error in input" + departments.toString());
			throw new DepartmentNotFoundException("Please Pass all the fields..!");
		} catch (Exception e) {
			log.error("Add department end point stop execution due to error " + e.getMessage());
			throw new InternalError("Error with execution due to " + e.getMessage());
		}
	}

	@GetMapping(path = "/getDeptById")
	public ResponseEntity<DepartmentResponse> getDeptByID(@RequestParam Integer deptId) {
		log.info("getDeptByID end point starts.. with input request " + deptId);
		if (deptId != null && deptId != 0) {
			DepartmentResponse departById = serviceInf.getDepartById(deptId);
			log.info("output from service layer for departById " + departById.toString());
			return new ResponseEntity<DepartmentResponse>(departById, HttpStatus.CREATED);
		} else
			log.debug("getDeptByID end point stop execution due to error in input " + deptId);
		throw new DepartmentNotFoundException("Please Pass the valid Department Id");
	}

	@PatchMapping(path = "/updateDept")
	public ResponseEntity<DepartmentResponse> updateDepart(@RequestBody Departments departments) {
		try {
			log.info("updateDepart end point starts.. with input request " + departments.toString());
			if (departments != null && departments.getDepartmentId() != 0) {
				DepartmentResponse updateByDeptId = serviceInf.updateByDeptId(departments);
				log.info("output from service layer for updateDepart " + updateByDeptId.toString());
				return new ResponseEntity<DepartmentResponse>(updateByDeptId, HttpStatus.OK);
			} else
				log.debug("updateDepart end point stop execution due to error in input " + departments.toString());
			throw new DepartmentNotFoundException("Please pass all the required fields with valid data..!");
		} catch (Exception e) {
			log.error("updateDepart end point stop execution due to error " + e.getMessage());
			throw new InternalError("Error while execution due to " + e.getMessage());
		}
	}

	@DeleteMapping(path = "/deleteById/{deptId}")
	public ResponseEntity<DepartmentResponse> deleteDeptById(@PathVariable Integer deptId) {
		try {
			log.info("deleteDeptById end point starts.. with input request " + deptId);
			if (deptId != null && deptId != 0) {
				Boolean deleteByDeptId = serviceInf.deleteByDeptId(deptId);
				if (deleteByDeptId) {
					log.info("output from service layer for deleteDeptById " + deleteByDeptId
							+ " was deleted succefully..!");
					return new ResponseEntity<DepartmentResponse>(new DepartmentResponse(
							StandardResponse.success("Dept Details deleted successfully..! "), null), HttpStatus.OK);
				} else {
					log.debug("deleteDeptById end point stop execution due to error in input " + deptId);
					throw new DepartmentNotFoundException("Department details not found for given deptId: " + deptId);
				}
			} else
				log.debug("deleteDeptById end point stop execution due to error in input " + deptId);
			throw new DepartmentNotFoundException("Please pass all the required fields with valid data..!");
		} catch (Exception e) {
			log.error("deleteDeptById end point stop execution due to error " + e.getMessage());
			throw new InternalError("Error while execution due to " + e.getMessage());
		}
	}

}