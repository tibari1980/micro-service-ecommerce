package com.arcesi.micoroserviceecommerce.service.imp;

import org.springframework.stereotype.Service;

import com.arcesi.micoroserviceecommerce.entities.Category;
import com.arcesi.micoroserviceecommerce.repositories.CategoryRepository;
import com.arcesi.micoroserviceecommerce.repositories.ProductRepository;
import com.arcesi.micoroserviceecommerce.service.ICatalogueService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Data
@Slf4j
public class CatalogueServiceImp implements ICatalogueService {

	
	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	
	
}
