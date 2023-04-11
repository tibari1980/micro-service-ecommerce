package com.arcesi.micoroserviceecommerce.dtos.responses;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductResponse extends EntityAbstractResponse{

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
 	private CategoryResponse categoryResponse;
	
 	@Builder
 	public ProductResponse(Instant createdAt, Instant updatedAt, Boolean isActive, Long idProduct,
			String codeUniqueProduct, String designation, String description, double prixUnitaire, int quantiteStock,
			Boolean isDisponible, Boolean isPromotion, String imageProduct, CategoryResponse categoryResponse) {
		super(createdAt, updatedAt, isActive);
		this.idProduct = idProduct;
		this.codeUniqueProduct = codeUniqueProduct;
		this.designation = designation;
		this.description = description;
		this.prixUnitaire = prixUnitaire;
		this.quantiteStock = quantiteStock;
		this.isDisponible = isDisponible;
		this.isPromotion = isPromotion;
		this.imageProduct = imageProduct;
		this.categoryResponse=categoryResponse;
	}

 	
 	
}
