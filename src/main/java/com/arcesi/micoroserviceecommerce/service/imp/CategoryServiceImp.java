package com.arcesi.micoroserviceecommerce.service.imp;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.arcesi.micoroserviceecommerce.dtos.CategoryDTO;
import com.arcesi.micoroserviceecommerce.dtos.ProductDTO;
import com.arcesi.micoroserviceecommerce.entities.Category;
import com.arcesi.micoroserviceecommerce.entities.Product;
import com.arcesi.micoroserviceecommerce.exceptions.EntityNotFoundException;
import com.arcesi.micoroserviceecommerce.exceptions.InvalidEntityException;
import com.arcesi.micoroserviceecommerce.exceptions.enums.ErrorsCodesEnemuration;
import com.arcesi.micoroserviceecommerce.repositories.CategoryRepository;
import com.arcesi.micoroserviceecommerce.repositories.ProductRepository;
import com.arcesi.micoroserviceecommerce.service.ICategoryService;
import com.arcesi.micoroserviceecommerce.validators.CategoryValidators;
import com.arcesi.micoroserviceecommerce.validators.ProductValidators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
@Data
public class CategoryServiceImp implements ICategoryService {

	private CategoryRepository categoryRepository;
	private ProductRepository productRepository;

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

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		log.info("Inside méthode createCategory in Service CategoryServiceImp  objet category : {}", categoryDTO);
		List<String> errors = CategoryValidators.validate(categoryDTO);
		if (CollectionUtils.isNotEmpty(errors)) {
			log.error("Cateogry is not valid try again : {}", categoryDTO.toString());
			throw new InvalidEntityException("Category is not valide", ErrorsCodesEnemuration.CATEGORY_NOT_VALIDE,
					errors);
		}
		categoryDTO.setCodeUniqueCategory(UUID.randomUUID().toString());
		categoryDTO.setCreatedAt(Instant.now());
		categoryDTO.setIsActive(Boolean.TRUE);
		Category cateInOurDB = categoryRepository.findByLibelleCategoryIgnoreCase(categoryDTO.getLibelleCategory());
		if (cateInOurDB != null) {
			log.error("Categroy with Libelle : " + categoryDTO.getLibelleCategory()
					+ "exist in our data base try again!");
			throw new InvalidEntityException("Category existe in our data base",
					ErrorsCodesEnemuration.CATEGORY_NOT_FOUND);
		}
		Category category = categoryRepository.save(CategoryDTO.fromEntiry(categoryDTO));
		log.info("Category created successufully !!", category.toString());
		return CategoryDTO.toEntity(category);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDto, Long codeCateg) {
		log.info(
				"Inside méthode updateCategory in Service CategoryServiceImp  : category Dto : {} , identifiant category : {}",
				categoryDto.toString(), codeCateg);
		List<String> errors = CategoryValidators.validate(categoryDto);
		if (CollectionUtils.isNotEmpty(errors)) {
			log.error("Category is not valid try again  Errors :  {}", errors);
			throw new InvalidEntityException("Category is not valid try again ",
					ErrorsCodesEnemuration.CATEGORY_NOT_VALIDE, errors);
		}
		Category categoryWithLibelleExist = categoryRepository
				.findByLibelleCategoryIgnoreCase(categoryDto.getLibelleCategory());
		if (null != categoryWithLibelleExist) {
			throw new EntityNotFoundException(
					"Category is not valide with liblle : {} " + categoryDto.getLibelleCategory() + ".",
					ErrorsCodesEnemuration.CATEGORY_NOT_VALIDE);
		}
		// check if Category with codeCategory exist
		Category categoryInOurDB = categoryRepository.findById(codeCateg).orElseThrow(
				() -> new EntityNotFoundException("Category with id : " + codeCateg + "Not found in our data base."));
		categoryInOurDB.setLibelleCategory(categoryDto.getLibelleCategory());
		categoryInOurDB.setDescriptionCategory(categoryDto.getDescriptionCategory());
		categoryInOurDB.setUpdatedAt(Instant.now());
		log.info("Category updated successfully : {}", categoryInOurDB);
		return CategoryDTO.toEntity(categoryRepository.saveAndFlush(categoryInOurDB));
	}

	@Override
	public void deleteCategoryById(Long codeCategory) {
		log.info("Inside méthode deleteCategory in Service CategoryServiceImp id Category : {}", codeCategory);
		Category categoryDeleted = categoryRepository.findById(codeCategory)
				.orElseThrow(() -> new EntityNotFoundException(
						"Category with  : `" + codeCategory + "` not exist in our data base try again",
						ErrorsCodesEnemuration.CATEGORY_NOT_FOUND));
		if (categoryDeleted.getCodeCategory() != null) {
			categoryRepository.delete(categoryDeleted);
		}
		log.info("Category deleted successfully : {} ", categoryDeleted.toString());
	}

	@Override
	public void deleteAllCategories() {
		log.info("Inside méthode deleteAllCategories in Service CategoryServiceImpl ");
		categoryRepository.deleteAll();
		log.info("All Categories deleted successfully ");
	}

	@Override
	public ProductDTO createProduct(ProductDTO productDto, Long categoryId) {
		log.info("Inside méthode createProduct in Service CategoryServiceImp  objet ProductDTO : {} , Category Id : {}",
				productDto, categoryId);
		List<String> errors = ProductValidators.validate(productDto);
		if (CollectionUtils.isNotEmpty(errors)) {
			log.error("Product is not valid try again : List of Errors  :{}", errors);
			throw new InvalidEntityException("Product is not valid try again !",
					ErrorsCodesEnemuration.PRODUCT_NOT_VALIDE, errors);
		}
		Category findCategory = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new EntityNotFoundException(
						"Category with id :` " + categoryId + "` not found in our data base .",
						ErrorsCodesEnemuration.CATEGORY_NOT_FOUND));
		// check if designation exist in our data base
		Optional<Product> product = productRepository.findByDesignationIgnoreCase(productDto.getDesignation());

		if (product.isPresent()) {
			throw new InvalidEntityException("Product is not valid with this Designation : `"
					+ productDto.getDesignation() + "` please try again", ErrorsCodesEnemuration.PRODUCT_NOT_VALIDE);
		}
		productDto.setCategoryDTO(CategoryDTO.toEntity(findCategory));
		productDto.setCodeUniqueProduct(UUID.randomUUID().toString());
		productDto.setIsActive(Boolean.TRUE);
		productDto.setCreatedAt(Instant.now());
		Product productCreated = productRepository.save(ProductDTO.fromEntity(productDto));
		log.info("Product was created successfully ! : {} ", productCreated);
		return ProductDTO.toEntity(productCreated);
	}

}
