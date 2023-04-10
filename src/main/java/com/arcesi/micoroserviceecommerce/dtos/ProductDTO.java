package com.arcesi.micoroserviceecommerce.dtos;

import java.time.Instant;

import com.arcesi.micoroserviceecommerce.entities.Product;

import lombok.Builder;
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

 	@Builder
	public ProductDTO(Instant createdAt, Instant updatedAt, Boolean isActive, Long idProduct, String codeUniqueProduct,
			String designation, String description, double prixUnitaire, int quantiteStock, Boolean isDisponible,
			Boolean isPromotion, String imageProduct, CategoryDTO categoryDTO) {
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
		this.categoryDTO = categoryDTO;
	}
 	
 	
 	/**
 	 * Méthode permettant de mapper un bean vers un dto
 	 * @param bean {@link Product}
 	 * @return dto {@link ProductDTO }
 	 */
 	public static ProductDTO toEntity(Product bean)
 	{
 		if(null==bean) {
 			return null;
 		}
 		return ProductDTO.builder()
 				.idProduct(bean.getIdProduct())
 				.codeUniqueProduct(bean.getCodeUniqueProduct())
 				.createdAt(bean.getCreatedAt())
 				.updatedAt(bean.getUpdatedAt())
 				.categoryDTO(null)
 				.prixUnitaire(bean.getPrixUnitaire())
 				.quantiteStock(bean.getQuantiteStock())
 				.isDisponible(bean.getIsDisponible())
 				.isActive(bean.getIsActive())
 				.isPromotion(bean.getIsPromotion())
 				.imageProduct(bean.getImageProduct())
 				.build();
 	}

 	
 	/**
 	 * Méthode permettant de mapper un dto vers un bean
 	 * @param dto {@link ProductDTO }
 	 * @return bean {@link ProductDTO }
 	 */
 	public static Product fromEntity(ProductDTO dto) {
 		if(null==dto)
 		{
 			return null;
 		}
 		return Product.builder()
 				.idProduct(dto.getIdProduct())
 				.codeUniqueProduct(dto.getCodeUniqueProduct())
 				.createdAt(dto.getCreatedAt())
 				.updatedAt(dto.getUpdatedAt())
 				.prixUnitaire(dto.getPrixUnitaire())
 				.quantiteStock(dto.getQuantiteStock())
 				.isActive(dto.getIsActive())
 				.isDisponible(dto.getIsDisponible())
 				.category(null)
 				.imageProduct(dto.getImageProduct())
 				.isPromotion(dto.getIsPromotion())
 				.description(dto.getDescription())
 				.designation(dto.getDesignation())
 				.build();
 	}
}
