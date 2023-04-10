package com.arcesi.micoroserviceecommerce.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductDTO  extends AbstractEntityDTO{

	private static final long serialVersionUID = 1L;
 	private Long idProduct;
 	private String codeUniqueProduct;
 	private String designation;
	
 	private String description;
 	private double prixUnitaire;
 	private int quantiteStock;
 	private Boolean isDisponible;
 	private Boolean isPromotion;
 	private String imageProduct;
	
 	private CategoryDTO categoryDTO;

}
