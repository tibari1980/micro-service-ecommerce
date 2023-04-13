package com.arcesi.micoroserviceecommerce.web.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcesi.micoroserviceecommerce.dtos.ProductDTO;
import com.arcesi.micoroserviceecommerce.dtos.responses.ProductResponse;
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
	public ResponseEntity<List<ProductResponse>> findAllProduct(int page, int limit) {
		log.info("Inside methode findAllProduct in Controllers ProductRestControllers  page : {} , limit :{}",page,limit);
		List<ProductDTO> productDTOs=iProductService.findAllProduct(page, limit);
		List<ProductResponse> productResponses=productDTOs.stream().map(ProductDTO::productDtoToProductResponse).collect(Collectors.toList());
		if(productResponses.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductResponse>>(productResponses,HttpStatus.OK);
	}

	
}
