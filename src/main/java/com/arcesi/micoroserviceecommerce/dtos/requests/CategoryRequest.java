package com.arcesi.micoroserviceecommerce.dtos.requests;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter @Setter
public class CategoryRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long codeCategory;
 	private String libelleCategory;
 	private String descriptionCategory;
	
 	@Builder
 	public CategoryRequest(Long codeCategory, String libelleCategory, String descriptionCategory) {
		super();
		this.codeCategory = codeCategory;
		this.libelleCategory = libelleCategory;
		this.descriptionCategory = descriptionCategory;
	}
 	
 	
    
 	
}
