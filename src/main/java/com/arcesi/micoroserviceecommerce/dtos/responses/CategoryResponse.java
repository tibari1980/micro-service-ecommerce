package com.arcesi.micoroserviceecommerce.dtos.responses;

import java.time.Instant;
import java.util.Collection;

import com.arcesi.micoroserviceecommerce.dtos.ProductDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter @Setter
public class CategoryResponse  extends EntityAbstractResponse{

	private static final long serialVersionUID = 1L;

	 
	private Long codeCategory;
	 
	private String codeUniqueCategory;
 	private String libelleCategory;
 	private String descriptionCategory;
 	private Collection<ProductDTO> productDTOs;
	
 	@Builder
 	public CategoryResponse(Instant createdAt, Instant updatedAt, Boolean isActive, Long codeCategory,
			String codeUniqueCategory, String libelleCategory, String descriptionCategory,
			Collection<ProductDTO> productDTOs) {
		super(createdAt, updatedAt, isActive);
		this.codeCategory = codeCategory;
		this.codeUniqueCategory = codeUniqueCategory;
		this.libelleCategory = libelleCategory;
		this.descriptionCategory = descriptionCategory;
		this.productDTOs = productDTOs;
	}
 	
 	
}
