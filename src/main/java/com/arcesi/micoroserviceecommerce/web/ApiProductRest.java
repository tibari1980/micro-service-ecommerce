package com.arcesi.micoroserviceecommerce.web;

import java.util.List;

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

	@GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductResponse>> findAllProduct(@RequestParam(name="page",defaultValue = "0") final int page,@RequestParam(name="limit",defaultValue = "10")final int limit);

	
	@PutMapping(value="/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest,@PathVariable("idProduct") Long idProduct);
	
	@GetMapping(value="/findById/{idProduct}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable("idProduct") String idProduct);

    @GetMapping(value="/findByCodeUnique/{codeUnique}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResponse> getProductByCodeUnique(@PathVariable(value="") String codeUnique);


    @GetMapping(value="/findAllProductIsActive",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductResponse>> findAllProductsIsActive(@RequestParam(name="page",defaultValue = "0")final int page ,@RequestParam(name="limit",defaultValue = "10") final int limit);

    @GetMapping(value="/findAllProductByCategoryId/{codeCategory}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductResponse>> getAllProductByCategory(@PathVariable(name="codeCategory") String codeCategory,@RequestParam(name="page",defaultValue = "0") final int page ,@RequestParam(name="limit",defaultValue = "10") final int limit);
}
