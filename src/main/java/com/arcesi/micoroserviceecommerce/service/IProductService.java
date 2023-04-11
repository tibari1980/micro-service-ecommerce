package com.arcesi.micoroserviceecommerce.service;

import java.util.List;

import com.arcesi.micoroserviceecommerce.dtos.ProductDTO;

public interface IProductService {

	
	public ProductDTO createProduct(final ProductDTO productDTO);
	
	public List<ProductDTO> findAllProduct(final int page,final int limit);
}
