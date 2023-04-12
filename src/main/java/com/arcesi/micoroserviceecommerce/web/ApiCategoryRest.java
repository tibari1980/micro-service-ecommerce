package com.arcesi.micoroserviceecommerce.web;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.arcesi.micoroserviceecommerce.dtos.responses.CategoryResponse;

public interface ApiCategoryRest {

	@GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryResponse>> getAllCategories(@RequestParam(name="title",defaultValue = "") final String libelle,
			@RequestParam(name = "page", defaultValue = "0") final int page,
			@RequestParam(name = "limit", defaultValue = "10") final int limit);

	@GetMapping(value="/findCategoryById/{idCategory}",produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable(name="idCategory") final Long idCategory);


	@GetMapping(value="/findBycodeUniqueCategory/{codeUniqueCategory}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> getCategoryByCodeUnique(@PathVariable(value = "codeUniqueCategory") final String codeUnique);

	@GetMapping(value ="/findByIsActive",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryResponse>> findAllCategoryIsActive(@RequestParam(name = "page",defaultValue = "0") final int page,@RequestParam(name="limit",defaultValue = "10") final int limit);
}
