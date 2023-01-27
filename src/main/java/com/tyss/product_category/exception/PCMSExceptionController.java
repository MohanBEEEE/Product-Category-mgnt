package com.tyss.product_category.exception;

import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tyss.product_category.model.response.StandardResponse;

import lombok.val;

@ControllerAdvice
public class PCMSExceptionController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = DepartmentNotFoundException.class)
	public ResponseEntity<StandardResponse> deptNotFoundExp(DepartmentNotFoundException exception){
		return ResponseEntity.badRequest().body(StandardResponse.badRequest(exception.getMsg()));
	}
	
	@ExceptionHandler(value = InternalError.class)
	public ResponseEntity<StandardResponse> deptInternalServerError(InternalError exception){
		return new ResponseEntity<StandardResponse>(StandardResponse.internalServerError(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = NoSuchElementException.class)
	public ResponseEntity<StandardResponse> deptNoSuchElementException(NoSuchElementException exception){
		return new ResponseEntity<StandardResponse>(StandardResponse.failedError("400", exception.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = EmptyResultDataAccessException.class)
	public ResponseEntity<StandardResponse> deptEmptyResultDataAccessException(EmptyResultDataAccessException exception){
		return new ResponseEntity<StandardResponse>(StandardResponse.failedError("400", exception.getMessage()), HttpStatus.BAD_REQUEST);
	}
}
