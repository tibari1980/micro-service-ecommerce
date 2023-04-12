package com.arcesi.micoroserviceecommerce.web.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcesi.micoroserviceecommerce.dtos.CategoryDTO;
import com.arcesi.micoroserviceecommerce.dtos.responses.CategoryResponse;
import com.arcesi.micoroserviceecommerce.service.ICategoryService;
import com.arcesi.micoroserviceecommerce.web.ApiCategoryRest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Data
@Slf4j
@RequestMapping(value = "/api/v1/categories")
public class CategoryRestController implements ApiCategoryRest {

	private ICategoryService iCategoryService;

	@Override
	public ResponseEntity<List<CategoryResponse>> getAllCategories(String libelle, int page, int limit) {
		log.info(
				"Inside methode getAllCategories in Controller CategroyRestController liblle : {} , pagee : {} , limi : {} ",
				libelle, page, libelle);
		List<CategoryDTO> categoryDTOs = iCategoryService.findByLibelleCategoryContaining(libelle, page, limit);
		if (categoryDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		List<CategoryResponse> categoryResponses = categoryDTOs.stream().map(CategoryDTO::CategoryDtoToCategoryResponse)
				.collect(Collectors.toList());
		return new ResponseEntity<List<CategoryResponse>>(categoryResponses, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CategoryResponse> getCategoryById(Long idCategory) {
		log.info("Inside méthode getCategoryByid in Controller : CategoryRestController identifiant Category : {} ",
				idCategory);
		if (null == idCategory) {
			return null;
		}
		CategoryDTO categoryDTO = iCategoryService.findByCodeCategory(idCategory);
		return new ResponseEntity<CategoryResponse>(CategoryDTO.CategoryDtoToCategoryResponse(categoryDTO),
				HttpStatus.OK);
	}

}
