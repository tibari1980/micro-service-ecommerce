package com.arcesi.micoroserviceecommerce.dtos;

import java.time.Instant;

import com.arcesi.micoroserviceecommerce.dtos.requests.CategoryRequest;
import com.arcesi.micoroserviceecommerce.dtos.responses.CategoryResponse;
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
 	 
	
 	@Builder
	public CategoryDTO(Instant createdAt, Instant updatedAt, Boolean isActive, Long codeCategory,
			String codeUniqueCategory, String libelleCategory, String descriptionCategory) {
		super(createdAt, updatedAt, isActive);
		this.codeCategory = codeCategory;
		this.codeUniqueCategory = codeUniqueCategory;
		this.libelleCategory = libelleCategory;
		this.descriptionCategory = descriptionCategory;
		 
	}
 	
 	/**
 	 * Méthode permettant de mapper un objet bean vers un objet dto
 	 * @param bean {@link Category}
 	 * @return dto {@link CategoryDTO}
 	 */
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
 				.isActive(bean.getIsActive())
 				.build();
 	}
 	
 	/**
 	 * Méthode permettant de mapper un objet dto vers un objet bean
 	 * @param dto {@link CategoryDTO}
 	 * @return bean {@link Category}
 	 */
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
 				.descriptionCategory(dto.getDescriptionCategory())
 				.libelleCategory(dto.getLibelleCategory())
 				.build();
 	}

 	
 	/**
 	 * Méthode permettant de mapper un oject dto vers un objet response
 	 * @param dto {@link CategoryDTO}
 	 * @return response {@link CategoryResponse}
 	 */
 	public static CategoryResponse CategoryDtoToCategoryResponse(CategoryDTO dto) {
 		if(null==dto) {
 			return null;
 		}
 		
 		return CategoryResponse.builder()
 				.codeCategory(dto.getCodeCategory())
 				.codeUniqueCategory(dto.getCodeUniqueCategory())
 				.createdAt(dto.getCreatedAt())
 				.updatedAt(dto.getUpdatedAt())
 				.isActive(dto.getIsActive())
 				.libelleCategory(dto.getLibelleCategory())
 				.descriptionCategory(dto.getDescriptionCategory())
 				.build();
 	}
 	
 	
 	public static CategoryDTO categoryRequestToCategoryDto(CategoryRequest bean) {
 		if(null==bean) {
 			return null;
 		}
 		return CategoryDTO.builder()
 				.codeCategory(bean.getCodeCategory())
 				.libelleCategory(bean.getLibelleCategory())
 				.descriptionCategory(bean.getDescriptionCategory())
 				.build();
 	}
}
