package com.arcesi.micoroserviceecommerce.web;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.arcesi.micoroserviceecommerce.dtos.requests.CategoryRequest;
import com.arcesi.micoroserviceecommerce.dtos.requests.ProductRequest;
import com.arcesi.micoroserviceecommerce.dtos.responses.CategoryResponse;
import com.arcesi.micoroserviceecommerce.dtos.responses.ProductResponse;

public interface ApiCategoryRest {

	@GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryResponse>> getAllCategories(
			@RequestParam(name = "title", defaultValue = "") final String libelle,
			@RequestParam(name = "page", defaultValue = "0") final int page,
			@RequestParam(name = "limit", defaultValue = "10") final int limit);

	@GetMapping(value = "/findCategoryById/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable(name = "idCategory") final String idCategory);

	@GetMapping(value = "/findBycodeUniqueCategory/{codeUniqueCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> getCategoryByCodeUnique(
			@PathVariable(value = "codeUniqueCategory") final String codeUnique);

	@GetMapping(value = "/findByIsActive", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryResponse>> findAllCategoryIsActive(
			@RequestParam(name = "page", defaultValue = "0") final int page,
			@RequestParam(name = "limit", defaultValue = "10") final int limit);

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest);

	@PutMapping(value = "/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> updateCategory(@RequestBody CategoryRequest cat,
			@PathVariable(name = "codeCategory") Long codeCategory);

	@DeleteMapping(value = { "/", "", "/{codeCategory}" })
	public ResponseEntity<HttpStatus> deleteCategory(@PathVariable Optional<String> codeCategory);

	@DeleteMapping(value = "/deleteAllCategories")
	public ResponseEntity<HttpStatus> deleteAllCategories();

	@PostMapping(value = "/{CategoryId}/products")
	public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest,
			@PathVariable("CategoryId") Long CategoryId);

}
