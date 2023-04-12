package com.arcesi.micoroserviceecommerce.entities;

import java.time.Instant;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="PRODUCTS")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Product extends AbstractEntity{

	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_PRODUCT")
	private Long idProduct;
	@Column(name="CODE_UNIQUE_PRODUCT")
	private String codeUniqueProduct;
	@Column(name="DESIGNATION",unique = true,insertable = true,updatable = true,length = 60)
	private String designation;
	
	@Column(name="DESCRIPTION",insertable = true,updatable = true,length = 255)
	private String description;
	@Column(name="PRIX_UNITAIRE",insertable = true,updatable = true)
	private double prixUnitaire;
	@Column(name="QUANTITE_STOCK",insertable = true,updatable = true)
	private int quantiteStock;
	@Column(name="IS_DISPONIBLE",insertable = true,updatable = true)
	private Boolean isDisponible;
	@Column(name="IS_PORMOTION",insertable = true,updatable = true)
	private Boolean isPromotion;
	@Column(name="IMAGE_PRODUCT")
	private String imageProduct;
	
	@ManyToOne(fetch = FetchType.EAGER,optional = false)
	@JoinColumn(name="CODE_CATEGORY",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Category category;

	@Builder
	public Product(Instant createdAt, Instant updatedAt, Boolean isActive, Long idProduct, String codeUniqueProduct,
			String designation, String description, double prixUnitaire, int quantiteStock, Boolean isDisponible,
			Boolean isPromotion, String imageProduct, Category category) {
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
		this.category = category;
	}

	 
	 
	
	

}
