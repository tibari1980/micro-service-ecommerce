package com.arcesi.micoroserviceecommerce.service;

import java.util.List;

import com.arcesi.micoroserviceecommerce.dtos.ProductDTO;

public interface IProductService {

	
	 
	
	public List<ProductDTO> findAllProduct(final String libelle, final int page,final int limit);

	public ProductDTO updateProduct(final ProductDTO pro, Long idProduct);

	public ProductDTO getProductById(Long codeProduct);

	public ProductDTO getProductByCodeUnique(final String codeUnique);

	public List<ProductDTO> findAllIsActive(final boolean b,final int page, final int limit);

	public List<ProductDTO> getAllProductByCategoryActive(String codeCategory, int page, int limit);

	public List<ProductDTO> getAllProductDisponibleEnPromotionAvecPrixCategory(Boolean isDisponible,
			Boolean isPromotion, Double prixUnitaire, int page, int limit, Long codeCategory);
}
