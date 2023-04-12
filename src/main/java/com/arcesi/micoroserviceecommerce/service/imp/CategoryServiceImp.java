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
import com.arcesi.micoroserviceecommerce.exceptions.EntityNotFoundException;
import com.arcesi.micoroserviceecommerce.exceptions.enums.ErrorsCodesEnemuration;
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
		log.info("Inside method findAllCategories in Service CategoryServiceImp : libelle : {}  page : {} , limi : {} ",
				libelle, page, limit);
		if (page > 0) {
			page = page - 1;
		}
		Pageable pageable = PageRequest.of(page, limit, Sort.by("codeCategory").descending());
		Page<Category> pageCategory = categoryRepository.findByLibelleCategoryContaining(libelle, pageable);
		List<Category> lstCategories = pageCategory.getContent();
		List<CategoryDTO> lstCategoriesDTos = lstCategories.stream().map(CategoryDTO::toEntity)
				.collect(Collectors.toList());
		return lstCategoriesDTos;
	}

	@Override
	public CategoryDTO findByCodeCategory(Long idCategory) {
		log.info("Inside methode findByCodeCategory in Service CategoryServiceImpl  identifiant category : {}",
				idCategory);
		Category category = categoryRepository.findById(idCategory)
				.orElseThrow(() -> new EntityNotFoundException(
						"Category N°: " + idCategory + " Not Found in our data base try again !",
						ErrorsCodesEnemuration.CATEGORY_NOT_FOUND));
		return CategoryDTO.toEntity(category);
	}

	@Override
	public CategoryDTO findByCodeUniqueCategory(String codeUnique) {
		log.info("Inside methode findByCodeUniqueCateogry() in Service CategoryServiceImp  Code unique : {} ",
				codeUnique);
		Category category = categoryRepository.findByCodeUniqueCategory(codeUnique);
		if (null == category) {
			log.error("Category with code unique : {} Not found in our data base try again !! : ", codeUnique);
			throw new EntityNotFoundException(
					"Category code Unique N°: " + codeUnique + " Not Found in our data base try again !",
					ErrorsCodesEnemuration.CATEGORY_NOT_FOUND);
		}
		log.info("Category Founded Successfully !!", category.toString());
		return CategoryDTO.toEntity(category);
	}

	 

	@Override
	public List<CategoryDTO> findByIsActive(Boolean value, int page, int limit) {
		log.info("Inside methode findByIsActive() in Service CategoryServiceImp ");
		if (page > 0) {
			page = page - 1;
		}
		Pageable pageable = PageRequest.of(page, limit, Sort.by("codeCategory").descending());
		Page<Category> pageCategory = categoryRepository.findByIsActive(true, pageable);
		List<Category> lstCategories = pageCategory.getContent();
		List<CategoryDTO> lstCategoriesDTos = lstCategories.stream().map(CategoryDTO::toEntity)
				.collect(Collectors.toList());
		return lstCategoriesDTos;
	}

}
