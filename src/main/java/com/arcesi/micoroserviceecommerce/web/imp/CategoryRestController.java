package com.arcesi.micoroserviceecommerce.web.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcesi.micoroserviceecommerce.dtos.CategoryDTO;
import com.arcesi.micoroserviceecommerce.dtos.ProductDTO;
import com.arcesi.micoroserviceecommerce.dtos.requests.CategoryRequest;
import com.arcesi.micoroserviceecommerce.dtos.requests.ProductRequest;
import com.arcesi.micoroserviceecommerce.dtos.responses.CategoryResponse;
import com.arcesi.micoroserviceecommerce.dtos.responses.ProductResponse;
import com.arcesi.micoroserviceecommerce.exceptions.InvalidEntityException;
import com.arcesi.micoroserviceecommerce.exceptions.enums.ErrorsCodesEnemuration;
import com.arcesi.micoroserviceecommerce.service.ICategoryService;
import com.arcesi.micoroserviceecommerce.web.ApiCategoryRest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:8080")

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
	public ResponseEntity<CategoryResponse> getCategoryById(String idCategory) {
		log.info("Inside méthode getCategoryByid in Controller : CategoryRestController identifiant Category : {} ",
				idCategory);
		if (!StringUtils.isNumeric(idCategory)) {
			throw new InvalidEntityException(
					"Parametre  : code Category `" + idCategory + "`  is not valid please try again ",
					ErrorsCodesEnemuration.CATEGORY_NOT_VALIDE);
		}
		CategoryDTO categoryDTO = iCategoryService.findByCodeCategory(Long.parseLong(idCategory));
		return new ResponseEntity<CategoryResponse>(CategoryDTO.CategoryDtoToCategoryResponse(categoryDTO),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CategoryResponse> getCategoryByCodeUnique(String codeUnique) {
		log.info("Inside méthode getCategoryByCodeUniqueCateogy in CategroyRestController  codeUniqueCategory : {} ",
				codeUnique);
		if (StringUtils.isEmpty(codeUnique)) {
			log.error("Code Unqie : {}  Not valide try again ! ", codeUnique);
			return null;
		}
		CategoryDTO categoryDTO = iCategoryService.findByCodeUniqueCategory(codeUnique);
		return new ResponseEntity<CategoryResponse>(CategoryDTO.CategoryDtoToCategoryResponse(categoryDTO),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<CategoryResponse>> findAllCategoryIsActive(final int page, final int limit) {
		log.info("Inside methode findAllCateogryIsActive in Controller CategoryRestController  ");
		List<CategoryDTO> categoryDTOs = iCategoryService.findByIsActive(true, page, limit);
		if (CollectionUtils.isEmpty(categoryDTOs)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		List<CategoryResponse> categoryResponses = categoryDTOs.stream().map(CategoryDTO::CategoryDtoToCategoryResponse)
				.collect(Collectors.toList());
		return new ResponseEntity<List<CategoryResponse>>(categoryResponses, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CategoryResponse> createCategory(CategoryRequest categoryRequest) {
		log.info("Inside methode createCategory in CategoryRestController category  : {}", categoryRequest);
		CategoryDTO categoryDTOCreated = iCategoryService
				.createCategory(CategoryDTO.categoryRequestToCategoryDto(categoryRequest));

		return new ResponseEntity<CategoryResponse>(CategoryDTO.CategoryDtoToCategoryResponse(categoryDTOCreated),
				HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CategoryResponse> updateCategory(CategoryRequest cat, Long codeCateg) {
		log.info(
				"Inside methode updateCategory in Controller CategoryRestController  categoryRequest :  {} , identifiant Category : {}",
				cat.toString(), codeCateg);
		CategoryDTO categoryDTO = iCategoryService.updateCategory(CategoryDTO.categoryRequestToCategoryDto(cat),
				codeCateg);

		return new ResponseEntity<CategoryResponse>(CategoryDTO.CategoryDtoToCategoryResponse(categoryDTO),
				HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<HttpStatus> deleteCategory(Optional<String> codeCategory) {
		log.info("Inside méthode deleteCategory in Controller CategoryRestController : identifiant Category : {}",
				codeCategory);
		if (codeCategory.isPresent()) {
			if(!StringUtils.isNumeric(codeCategory.get()))
			{
				throw new InvalidEntityException(
						"Parametre  : code Category `" + codeCategory + "`  is not valid please try again ",
						ErrorsCodesEnemuration.CATEGORY_NOT_VALIDE);	
			}
			
		}else if(codeCategory.isEmpty()) {
			throw new InvalidEntityException("Parametre id Category is not valid try again",ErrorsCodesEnemuration.CATEGORY_NOT_VALIDE);
		}
		iCategoryService.deleteCategoryById(Long.parseLong(codeCategory.get()));
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<HttpStatus> deleteAllCategories() {
		log.info("Inside méthode deleteAllCategories in Controller CategoryRestController ");
		iCategoryService.deleteAllCategories();
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<ProductResponse> createProduct(ProductRequest productRequest, Long categoryId) {
		log.info("Inside méthode createProduct in Controller CategoryRestController  products: {} ,identifiant category : {}",productRequest.toString(), categoryId);
		ProductDTO productDTO=iCategoryService.createProduct(ProductDTO.productRequestToProductDto(productRequest),categoryId);
		
		return new ResponseEntity<ProductResponse>(ProductDTO.productDtoToProductResponse(productDTO),HttpStatus.CREATED);
	}

	

}
