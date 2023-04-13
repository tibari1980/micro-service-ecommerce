package com.arcesi.micoroserviceecommerce.dtos;

import java.time.Instant;

import com.arcesi.micoroserviceecommerce.dtos.requests.ProductRequest;
import com.arcesi.micoroserviceecommerce.dtos.responses.ProductResponse;
import com.arcesi.micoroserviceecommerce.entities.Product;
import com.arcesi.micoroserviceecommerce.exceptions.InvalidEntityException;
import com.arcesi.micoroserviceecommerce.exceptions.enums.ErrorsCodesEnemuration;

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
 			throw new InvalidEntityException("Product is not valid try again",ErrorsCodesEnemuration.PRODUCT_NOT_VALIDE);
 		}
 		return ProductDTO.builder()
 				.idProduct(bean.getIdProduct())
 				.codeUniqueProduct(bean.getCodeUniqueProduct())
 				.description(bean.getDescription())
 				.designation(bean.getDesignation())
 				.createdAt(bean.getCreatedAt())
 				.updatedAt(bean.getUpdatedAt())
 				.categoryDTO(CategoryDTO.toEntity(bean.getCategory()))
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
 			throw new InvalidEntityException("Product is not valid try again" , ErrorsCodesEnemuration.PRODUCT_NOT_VALIDE);
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
 				.category(CategoryDTO.fromEntiry(dto.getCategoryDTO()))
 				.imageProduct(dto.getImageProduct())
 				.isPromotion(dto.getIsPromotion())
 				.description(dto.getDescription())
 				.designation(dto.getDesignation())
 				.build();
 	}
 	
 	/**
 	 * Méthode permettant de mapper un dto vers un object response
 	 * @param dto {@link ProductDTO}
 	 * @return response {@link ProductResponse}
 	 */
 	public static ProductResponse  productDtoToProductResponse(ProductDTO dto) {
 		if(null==dto) {
 			throw new InvalidEntityException("Product is not valid try again please",ErrorsCodesEnemuration.PRODUCT_NOT_VALIDE);
 		}
 		return ProductResponse.builder()
 				.codeUniqueProduct(dto.getCodeUniqueProduct())
 				.idProduct(dto.getIdProduct())
 				.createdAt(dto.getCreatedAt())
 				.updatedAt(dto.getUpdatedAt())
 				.description(dto.getDescription())
 				.designation(dto.getDesignation())
 				.quantiteStock(dto.getQuantiteStock())
 				.prixUnitaire(dto.getPrixUnitaire())
 				.isActive(dto.getIsActive())
 				.isPromotion(dto.getIsPromotion())
 				.isDisponible(dto.getIsDisponible())
 				.imageProduct(dto.getImageProduct())
 				.categoryResponse(CategoryDTO.CategoryDtoToCategoryResponse(dto.getCategoryDTO()))
 				.build();
 	}
 	
 	/**
 	 * Méthode permettant de mapper un objet request vers un objet dto
 	 * @param request {@link ProductRequest}
 	 * @return dto {@link ProductDTO}
 	 */
 	public static ProductDTO productRequestToProductDto(ProductRequest request)
 	{
 		if(null==request) {
 			throw new InvalidEntityException("Product is not valid try again please",ErrorsCodesEnemuration.PRODUCT_NOT_VALIDE);
 		}
 		return ProductDTO.builder()
 				.description(request.getDescription())
 				.designation(request.getDesignation())
 				.imageProduct(request.getImageProduct())
 				.isDisponible(request.getIsDisponible())
 				.isPromotion(request.getIsPromotion())
 				.prixUnitaire(request.getPrixUnitaire())
 				.quantiteStock(request.getQuantiteStock())
 				.categoryDTO(null)
 				.build();
 	}
}
