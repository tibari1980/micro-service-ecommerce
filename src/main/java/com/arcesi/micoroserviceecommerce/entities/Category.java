package com.arcesi.micoroserviceecommerce.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "CATEGORIES")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Category extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODE_CATEGORY")
	private Long codeCategory;
	@Column(name = "CODE_UNIQUE_CATEGORY", insertable = true, updatable = false, unique = true)
	private String codeUniqueCategory;
	@Column(name = "LIBELLE_CATEGORY", insertable = true, unique = true, updatable = true, nullable = false)
	private String libelleCategory;
	@Column(name = "DESCRIPTION_CATEGORY", nullable = false, insertable = true, length = 255)
	private String descriptionCategory;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Collection<Product> products;

	@Builder
	public Category(Instant createdAt, Instant updatedAt, Boolean isActive, Long codeCategory,
			String codeUniqueCategory, String libelleCategory, String descriptionCategory,
			Collection<Product> products) {
		super(createdAt, updatedAt, isActive);
		this.codeCategory = codeCategory;
		this.codeUniqueCategory = codeUniqueCategory;
		this.libelleCategory = libelleCategory;
		this.descriptionCategory = descriptionCategory;
		this.products = products;
	}

	 
	
	
	
	 

	
}
