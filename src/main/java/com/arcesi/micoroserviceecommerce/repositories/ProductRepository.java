package com.arcesi.micoroserviceecommerce.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.arcesi.micoroserviceecommerce.entities.Product;



public interface ProductRepository extends JpaRepository<Product,Long> {

	Optional<Product> findByDesignationIgnoreCase(String designation);

	Page<Product> findByIsActive(boolean b, Pageable pageable);

	Page<Product> findByIsActiveTrueAndCategoryCodeCategory(Long codeCategory, Pageable pageable);

}
