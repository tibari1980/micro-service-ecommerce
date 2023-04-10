package com.arcesi.micoroserviceecommerce.dtos;

import java.time.Instant;
import java.util.Collection;

import com.arcesi.micoroserviceecommerce.entities.Category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter @Setter
public class CategoryDTO extends AbstractEntityDTO {

	private static final long serialVersionUID = 1L;

	 
	private Long codeCategory;
	 
	private String codeUniqueCategory;
 	private String libelleCategory;
 	private String descriptionCategory;
 	private Collection<ProductDTO> productDTOs;
	
 	@Builder
	public CategoryDTO(Instant createdAt, Instant updatedAt, Boolean isActive, Long codeCategory,
			String codeUniqueCategory, String libelleCategory, String descriptionCategory,
			Collection<ProductDTO> productDTOs) {
		super(createdAt, updatedAt, isActive);
		this.codeCategory = codeCategory;
		this.codeUniqueCategory = codeUniqueCategory;
		this.libelleCategory = libelleCategory;
		this.descriptionCategory = descriptionCategory;
		this.productDTOs = productDTOs;
	}
 	
 	
 	public static CategoryDTO toEntity(Category bean) {
 		if(null==bean) {
 			return null;
 		}
 		return CategoryDTO.builder()
 				.codeCategory(bean.getCodeCategory())
 				.codeUniqueCategory(bean.getCodeUniqueCategory())
 				.createdAt(bean.getCreatedAt())
 				.updatedAt(bean.getUpdatedAt())
 				.libelleCategory(bean.getLibelleCategory())
 				.descriptionCategory(bean.getDescriptionCategory())
 				.productDTOs(null)
 				.build();
 	}
 	
 	public static Category fromEntiry(CategoryDTO dto) {
 		if(null==dto) {
 			return null;
 		}
 		
 		return Category.builder()
 				.codeCategory(dto.getCodeCategory())
 				.codeUniqueCategory(dto.getCodeUniqueCategory())
 				.createdAt(dto.getCreatedAt())
 				.updatedAt(dto.getUpdatedAt())
 				.isActive(dto.getIsActive())
 				.products(null)
 				.build();
 	}

 	
}
