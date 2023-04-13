package com.arcesi.micoroserviceecommerce.service;

import java.util.List;

import com.arcesi.micoroserviceecommerce.dtos.CategoryDTO;

public interface ICategoryService {

	public List<CategoryDTO> findByLibelleCategoryContaining(final String libelle,final int page,final int limit);

	public CategoryDTO findByCodeCategory(final Long idCategory);

	public CategoryDTO findByCodeUniqueCategory(final String codeUnique);

	public List<CategoryDTO> findByIsActive(Boolean value,final int page, final  int limit);

	public CategoryDTO createCategory(final CategoryDTO categoryDTO);

	public CategoryDTO updateCategory(final CategoryDTO categoryRequestToCategoryDto,final Long codeCateg);

	public void deleteCategoryById(Long codeCategory);

	public void deleteAllCategories();
}
