package com.arcesi.micoroserviceecommerce.web.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcesi.micoroserviceecommerce.dtos.ProductDTO;
import com.arcesi.micoroserviceecommerce.dtos.requests.ProductRequest;
import com.arcesi.micoroserviceecommerce.dtos.responses.ProductResponse;
import com.arcesi.micoroserviceecommerce.exceptions.InvalidEntityException;
import com.arcesi.micoroserviceecommerce.exceptions.enums.ErrorsCodesEnemuration;
import com.arcesi.micoroserviceecommerce.service.IProductService;
import com.arcesi.micoroserviceecommerce.web.ApiProductRest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value="/api/v1/products")
public class ProductRestController implements ApiProductRest {

	private IProductService iProductService;
	@Override
	public ResponseEntity<List<ProductResponse>> findAllProduct(String designation, int page, int limit) {
		log.info("Inside methode findAllProduct in Controllers ProductRestControllers  page : {} , limit :{}",page,limit);
		List<ProductDTO> productDTOs=iProductService.findAllProduct(designation,page, limit);
		List<ProductResponse> productResponses=productDTOs.stream().map(ProductDTO::productDtoToProductResponse).collect(Collectors.toList());
		if(productResponses.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductResponse>>(productResponses,HttpStatus.OK);
	}
	@Override
	public ResponseEntity<ProductResponse> updateProduct(ProductRequest productRequest, Long idProduct) {
		log.info("Inside methode updateProduct in Controller ProductRestController  productRequest : {}  , idProduct : {} ",productRequest,idProduct);
		ProductDTO productDTO=iProductService.updateProduct(ProductDTO.productRequestToProductDto(productRequest),idProduct);
		
		return new ResponseEntity<ProductResponse>(ProductDTO.productDtoToProductResponse(productDTO),HttpStatus.ACCEPTED);
	}
	@Override
	public ResponseEntity<ProductResponse> getProductById(String codeProduct) {
		log.info("Inside methode getProductById in Controller ProductRestController codeProduct : {},",codeProduct);
		if(StringUtils.isBlank(codeProduct) || !StringUtils.isNumeric(codeProduct)) {
			log.error("Code product is not valid try again",codeProduct);
			throw new InvalidEntityException("Code Product :`"+codeProduct +"` is not valid try again",ErrorsCodesEnemuration.PRODUCT_NOT_VALIDE);
		}
		ProductDTO dto=iProductService.getProductById(Long.parseLong(codeProduct));
		return new ResponseEntity<ProductResponse>(ProductDTO.productDtoToProductResponse(dto),HttpStatus.OK);
	}
	@Override
	public ResponseEntity<ProductResponse> getProductByCodeUnique(String codeUnique) {
		 log.info("Inside methode getProductByCodeUnique in  ProductRestController code Unique : {} :",codeUnique);
		 if(StringUtils.isBlank(codeUnique)) {
			 log.error("Code Unique Product is not valid",codeUnique);
			 throw new InvalidEntityException("Code Product : `"+ codeUnique +"`is not valid try again" ,ErrorsCodesEnemuration.PRODUCT_NOT_VALIDE);
		 }
			ProductDTO productDTO = iProductService.getProductByCodeUnique(codeUnique);
		return new ResponseEntity<ProductResponse>(ProductDTO.productDtoToProductResponse(productDTO),HttpStatus.OK);
	}
	@Override
	public ResponseEntity<List<ProductResponse>> findAllProductsIsActive(int page, int limit) {
		log.info("Inside methode findAllProductIsActive in Controller ProductRestController  page : {} , limit : {} ",page,limit);
		List<ProductDTO> lstProductDTOs=iProductService.findAllIsActive(true,page,limit);
		List<ProductResponse> lstProductResponses=lstProductDTOs.stream().map(ProductDTO::productDtoToProductResponse).collect(Collectors.toList());
		if(CollectionUtils.isEmpty(lstProductResponses)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductResponse>>(lstProductResponses,HttpStatus.OK);
	}
	@Override
	public ResponseEntity<List<ProductResponse>> getAllProductByCategory(String codeCategory,final int page , final int limit) {
		log.info("Inside method getAllProductByCategory in Contorlller ProductRestController page : {} , limi : {} , code Category : {}",page,limit,codeCategory);
		if(StringUtils.isEmpty(codeCategory)|| !StringUtils.isNumeric(codeCategory)) {
			log.error("Cade Category is not valid try again  : {}",codeCategory);
			throw new InvalidEntityException("Code category :`"+ codeCategory +"` is not valid try again ",ErrorsCodesEnemuration.PRODUCT_NOT_VALIDE);
		}
		List<ProductDTO> lsDtos=iProductService.getAllProductByCategoryActive(codeCategory,page,limit);
		List<ProductResponse> lstResponses=lsDtos.stream().map(ProductDTO::productDtoToProductResponse).collect(Collectors.toList());
		if(CollectionUtils.isEmpty(lsDtos))
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductResponse>>(lstResponses,HttpStatus.OK);
	}
	@Override
	public ResponseEntity<List<ProductResponse>> findAllProductByCategoryDisponibleAndEnPromotionAndPrix(
			Boolean isDisponible, Boolean isPromotion, String prixUnitaire, int page, int limit, String codeCategory) {
		log.info("Inside methode findAllProductByCategoryDisponibleAndEnPromotionAndPrix in Controller  ProductRestController Disponible : {} , Promotion : {} , Prix unitaire : {} , Page : {} , Limit : {} , Cade Category : {} ", isDisponible,isPromotion,prixUnitaire,page,limit,codeCategory);
		if(StringUtils.isEmpty(codeCategory)|| !StringUtils.isNumeric(codeCategory)) {
			log.error("Cade Category is not valid try again  : {}",codeCategory);
			throw new InvalidEntityException("Code category :`"+ codeCategory +"` is not valid try again ",ErrorsCodesEnemuration.PRODUCT_NOT_VALIDE);
		}
		
		List<ProductDTO> lsDtos=iProductService.getAllProductDisponibleEnPromotionAvecPrixCategory(isDisponible,isPromotion,Double.parseDouble(prixUnitaire) ,page,limit,Long.parseLong(codeCategory));
		List<ProductResponse> lstResponses=lsDtos.stream().map(ProductDTO::productDtoToProductResponse).collect(Collectors.toList());
		if(CollectionUtils.isEmpty(lsDtos))
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductResponse>>(lstResponses,HttpStatus.OK);

	}
	 

	
}
