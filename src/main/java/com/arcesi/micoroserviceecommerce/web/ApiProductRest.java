package com.arcesi.micoroserviceecommerce.web;

import java.util.List;

import javax.print.attribute.standard.Media;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.arcesi.micoroserviceecommerce.dtos.requests.ProductRequest;
import com.arcesi.micoroserviceecommerce.dtos.responses.ProductResponse;

public interface ApiProductRest {

	//http://localhost:8080/api/v1/products/all?designation=LIVRE
	
	@GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductResponse>> findAllProduct(
			@RequestParam(name = "designation", defaultValue = "") final String designation,
			@RequestParam(name = "page", defaultValue = "0") final int page,
			@RequestParam(name = "limit", defaultValue = "10") final int limit);

	//http://localhost:8080/api/v1/products/1
	
	@PutMapping(value = "/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest,
			@PathVariable("idProduct") Long idProduct);

	//http://localhost:8080/api/v1/products/findById/1
	
	@GetMapping(value = "/findById/{idProduct}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable("idProduct") String idProduct);

	//http://localhost:8080/api/v1/products/findByCodeUnique/a1a169e3-bdcb-4ee3-92b5-981ca50e2431
	
	@GetMapping(value = "/findByCodeUnique/{codeUnique}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResponse> getProductByCodeUnique(@PathVariable(value = "") String codeUnique);

	//http://localhost:8080/api/v1/products/findAllProductIsActive
	
	@GetMapping(value = "/findAllProductIsActive", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductResponse>> findAllProductsIsActive(
			@RequestParam(name = "page", defaultValue = "0") final int page,
			@RequestParam(name = "limit", defaultValue = "10") final int limit);

	//http://localhost:8080/api/v1/products/findAllProductByCategoryId/1
	
	@GetMapping(value = "/findAllProductByCategoryId/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductResponse>> getAllProductByCategory(
			@PathVariable(name = "codeCategory") String codeCategory,
			@RequestParam(name = "page", defaultValue = "0") final int page,
			@RequestParam(name = "limit", defaultValue = "10") final int limit);

	 //http://localhost:8080/api/v1/products/findProductDisponiblreEnPromotionPrixUnitaireByCategory/2?prixUnitaire=260
	
	@GetMapping(value="/findProductDisponiblreEnPromotionPrixUnitaireByCategory/{codeCategory}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductResponse>> findAllProductByCategoryDisponibleAndEnPromotionAndPrix(
			@RequestParam(name = "isDisponible", defaultValue = "true") Boolean isDisponible,
			@RequestParam(name = "isPromotion", defaultValue = "true") Boolean isPromotion,
			@RequestParam(name = "prixUnitaire", defaultValue = "0") String prixUnitaire,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit,@PathVariable("codeCategory") String codeCategory);
}
