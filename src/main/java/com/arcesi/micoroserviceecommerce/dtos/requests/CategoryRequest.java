package com.arcesi.micoroserviceecommerce.dtos.requests;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter @Setter
@ToString
public class CategoryRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
 	private String libelleCategory;
 	private String descriptionCategory;
	
 	@Builder
 	public CategoryRequest( String libelleCategory, String descriptionCategory) {
		super();
 		this.libelleCategory = libelleCategory;
		this.descriptionCategory = descriptionCategory;
	}
 	
 	
    
 	
}
