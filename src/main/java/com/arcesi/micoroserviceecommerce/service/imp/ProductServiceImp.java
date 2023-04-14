package com.arcesi.micoroserviceecommerce.service.imp;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.arcesi.micoroserviceecommerce.dtos.ProductDTO;
import com.arcesi.micoroserviceecommerce.entities.Product;
import com.arcesi.micoroserviceecommerce.exceptions.EntityNotFoundException;
import com.arcesi.micoroserviceecommerce.exceptions.InvalidEntityException;
import com.arcesi.micoroserviceecommerce.exceptions.enums.ErrorsCodesEnemuration;
import com.arcesi.micoroserviceecommerce.repositories.ProductRepository;
import com.arcesi.micoroserviceecommerce.service.IProductService;
import com.arcesi.micoroserviceecommerce.validators.ProductValidators;

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
	public List<ProductDTO> findAllProduct(final String designation, int page, int limit) {
		log.info("Inside method findAllProduct in Service ProductServiceImp page : {} , limi : {}  , Designation : {}", page, limit,designation);
		if (page > 0) {
			page = page - 1;
		}
		Pageable pageable = PageRequest.of(page, limit, Sort.by("idProduct").descending());
		Page<Product> pageProducts = productRepository.findByDesignationContaining(designation,pageable);

		List<Product> lstProducts = pageProducts.getContent();
		List<ProductDTO> lstProductDTOS = lstProducts.stream().map(ProductDTO::toEntity).collect(Collectors.toList());
		return lstProductDTOS;
	}

	@Override
	public ProductDTO updateProduct(ProductDTO dto, Long idProduct) {
		log.info("Inside methode updateProduct in Service ProductServiceImp :productDTO {}, Idproduct : {}", dto,
				idProduct);
		List<String> errors = ProductValidators.validate(dto);
		if (CollectionUtils.isNotEmpty(errors)) {
			log.error("Product is not valid try again : Errors :  {} ", errors);
			throw new InvalidEntityException("Product is not valid try again ",
					ErrorsCodesEnemuration.PRODUCT_NOT_VALIDE, errors);
		}
		Product findProductInOurDB = productRepository.findById(idProduct)
				.orElseThrow(() -> new EntityNotFoundException(
						"Product  : " + idProduct + " not found in our data base try again",
						ErrorsCodesEnemuration.PRODUCT_NOT_FOUND));
		findProductInOurDB.setDescription(dto.getDescription());
		findProductInOurDB.setDesignation(dto.getDesignation());
		findProductInOurDB.setPrixUnitaire(dto.getPrixUnitaire());
		findProductInOurDB.setQuantiteStock(dto.getQuantiteStock());
		findProductInOurDB.setIsPromotion(dto.getIsPromotion());
		findProductInOurDB.setIsDisponible(dto.getIsDisponible());
		findProductInOurDB.setUpdatedAt(Instant.now());
		return ProductDTO.toEntity(productRepository.saveAndFlush(findProductInOurDB));
	}

	@Override
	public ProductDTO getProductById(Long codeProduct) {
		log.info("Inside methode getProductById in Service ProductServiceImp  codeProduct : {} ", codeProduct);
		Product product = productRepository.findById(codeProduct)
				.orElseThrow(() -> new EntityNotFoundException(
						"Product N° : `" + codeProduct + "`not found in our data base try again",
						ErrorsCodesEnemuration.PRODUCT_NOT_FOUND));
		return ProductDTO.toEntity(product);
	}

	@Override
	public ProductDTO getProductByCodeUnique(String codeUnique) {
		log.info("Inside Methode getProductByCodeUnique in Service ProductServiceImp  code unique product : {}",
				codeUnique);
		Product product = productRepository.findAll().stream()
				.filter(p -> p.getCodeUniqueProduct().equalsIgnoreCase(codeUnique)).findAny()
				.orElseThrow(() -> new EntityNotFoundException(
						"Product with code Unique :`" + codeUnique + "`was not found in ouar data base try again ",
						ErrorsCodesEnemuration.PRODUCT_NOT_FOUND));
		log.info("Product find successfully  product :{} ", product);
		return ProductDTO.toEntity(product);
	}

	@Override
	public List<ProductDTO> findAllIsActive(boolean b, int page, int limit) {
		log.info("Inside methode findallIsActive in Service ProductServiceImp  page : {} , limit : {} ", page ,limit);
		if(page >0) {
			page=page-1;
		}
		Pageable pageable=PageRequest.of(page, limit, Sort.by("idProduct").descending());
		Page<Product> pageProducts=productRepository.findByIsActive(true,pageable);
		List<Product> lstProducts=pageProducts.getContent();
		return lstProducts.stream().map(ProductDTO::toEntity).collect(Collectors.toList());
	}

	@Override
	public List<ProductDTO> getAllProductByCategoryActive(String codeCategory, int page, int limit) {
		log.info("Inside methode getAllProductByCategoryActive in Service ProductServiceImp  code category : {}, page : {} , limit : {}", codeCategory, page,limit);
		if(page>0) {
			page=page - 1;
		}
		Pageable pageable=PageRequest.of(page, limit,Sort.by("idProduct").ascending());
		Page<Product> pageProducts=productRepository.findByIsActiveTrueAndCategoryCodeCategory(Long.parseLong(codeCategory),pageable);
		List<Product> lsProducts=pageProducts.getContent();
		
		return lsProducts.stream().map(ProductDTO::toEntity).collect(Collectors.toList());
	}

	@Override
	public List<ProductDTO> getAllProductDisponibleEnPromotionAvecPrixCategory(Boolean isDisponible,
			Boolean isPromotion, Double prixUnitaire, int page, int limit, Long codeCategory) {
		log.info("Inside methode getAllProductDisponibleEnPromotionAvecPrixCategory in Service ProductServiceImp  Disponible : {} , Promotion : {} , Prix Unitaire : {} , Page : {} , Limit : {} , Code Category : {}",isDisponible,isPromotion,prixUnitaire,page,limit,codeCategory);
		if(page>0) {
			page=page-1;
		}
		Pageable pageable=PageRequest.of(page, limit, Sort.by("prixUnitaire").descending());
		Page<Product> pageProducts=productRepository.findByIsActiveTrueAndIsDisponibleTrueAndIsPromotionTrueAndPrixUnitaireGreaterThanEqualAndCategoryCodeCategory(prixUnitaire,codeCategory,pageable);
		
		List<Product> lstProducts=pageProducts.getContent();
		return lstProducts.stream().map(ProductDTO::toEntity).collect(Collectors.toList());
	}

}
