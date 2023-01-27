package com.tyss.product_category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.product_category.model.request.Categories;

@Repository
public interface CategoryRepoInf extends JpaRepository<Categories, Integer>{

}
