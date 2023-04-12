package com.arcesi.micoroserviceecommerce.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.arcesi.micoroserviceecommerce.dtos.CategoryDTO;
import com.arcesi.micoroserviceecommerce.entities.Category;
import com.arcesi.micoroserviceecommerce.repositories.CategoryRepository;
import com.arcesi.micoroserviceecommerce.service.ICategoryService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
@Data
public class CategoryServiceImp implements ICategoryService {

	private CategoryRepository categoryRepository;

	@Override
	public List<CategoryDTO> findByLibelleCategoryContaining(String libelle, int page, int limit) {
		log.info("Inside method findAllCategories in Service CategoryServiceImp : libelle : {}  page : {} , limi : {} ",libelle,page, limit);
		if (page > 0) {
			page = page - 1;
		}
		Pageable pageable = PageRequest.of(page, limit, Sort.by("codeCategory").descending());
		Page<Category> pageCategory = categoryRepository.findByLibelleCategoryContaining(libelle,pageable);
		List<Category> lstCategories = pageCategory.getContent();
		List<CategoryDTO> lstCategoriesDTos = lstCategories.stream().map(CategoryDTO::toEntity).collect(Collectors.toList());
		return lstCategoriesDTos;
	}

	@Override
	public CategoryDTO findByCodeCategory(Long idCategory) {
		log.info("Inside methode findByCodeCategory in Service CategoryServiceImpl  identifiant category : {}",idCategory);
		Category category=categoryRepository.findById(idCategory).orElseThrow(()->new RuntimeException("Not found Category"));
		return CategoryDTO.toEntity(category);
	}
   
	
}
