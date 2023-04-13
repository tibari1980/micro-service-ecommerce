package com.arcesi.micoroserviceecommerce.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.arcesi.micoroserviceecommerce.dtos.ProductDTO;
import com.arcesi.micoroserviceecommerce.entities.Product;
import com.arcesi.micoroserviceecommerce.exceptions.EntityNotFoundException;
import com.arcesi.micoroserviceecommerce.exceptions.enums.ErrorsCodesEnemuration;
import com.arcesi.micoroserviceecommerce.repositories.ProductRepository;
import com.arcesi.micoroserviceecommerce.service.IProductService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@Data
public class ProductServiceImp implements IProductService {

	private ProductRepository productRepository;

	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {

		return null;
	}

	@Override
	public List<ProductDTO> findAllProduct(int page, int limit) {
		log.info("Inside method findAllProduct in Service ProductServiceImp page : {} , limi : {} ", page, limit);
		if (page > 0) {
			page = page - 1;
		}
		Pageable pageable = PageRequest.of(page, limit, Sort.by("idProduct").descending());
		Page<Product> pageProducts = productRepository.findAll(pageable);

		List<Product> lstProducts = pageProducts.getContent();
		if (lstProducts.isEmpty()) {
			throw new EntityNotFoundException("List of product is empty ",ErrorsCodesEnemuration.PRODUCT_NOT_FOUND);
		}
		List<ProductDTO> lstProductDTOS = lstProducts.stream().map(ProductDTO::toEntity).collect(Collectors.toList());
		return lstProductDTOS;
	}

}
