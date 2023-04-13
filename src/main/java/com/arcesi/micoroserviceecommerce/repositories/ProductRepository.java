package com.arcesi.micoroserviceecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arcesi.micoroserviceecommerce.entities.Product;



public interface ProductRepository extends JpaRepository<Product,Long> {

	Optional<Product> findByDesignationIgnoreCase(String designation);

}
