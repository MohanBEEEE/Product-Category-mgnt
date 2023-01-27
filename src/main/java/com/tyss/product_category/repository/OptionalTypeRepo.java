package com.tyss.product_category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.product_category.model.request.OptionTypes;

public interface OptionalTypeRepo extends JpaRepository<OptionTypes, Integer>{
	
}
