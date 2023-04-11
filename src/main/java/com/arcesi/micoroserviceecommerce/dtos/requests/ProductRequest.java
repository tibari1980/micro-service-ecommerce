package com.arcesi.micoroserviceecommerce.dtos.requests;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequest implements Serializable{

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
	
 	private CategoryRequest categoryResquest;
}
