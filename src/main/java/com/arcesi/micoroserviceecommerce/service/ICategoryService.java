package com.arcesi.micoroserviceecommerce.service;

import java.util.List;

import com.arcesi.micoroserviceecommerce.dtos.CategoryDTO;

public interface ICategoryService {

	public List<CategoryDTO> findByLibelleCategoryContaining(final String libelle,final int page,final int limit);

	public CategoryDTO findByCodeCategory(final Long idCategory);
}
