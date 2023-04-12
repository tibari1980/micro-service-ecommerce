package com.arcesi.micoroserviceecommerce.web;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.arcesi.micoroserviceecommerce.dtos.responses.ProductResponse;

public interface ApiProductRest {

	@GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductResponse>> findAllProduct(@RequestParam(name="page",defaultValue = "0") final int page,@RequestParam(name="limit",defaultValue = "10")final int limit);
}
