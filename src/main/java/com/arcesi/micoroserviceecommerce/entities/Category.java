package com.arcesi.micoroserviceecommerce.entities;

import java.time.Instant;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Builder;
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
	@Column(name = "LIBELLE_CATEGORY",unique=true, insertable = true, updatable = true, nullable = false)
	private String libelleCategory;
	@Column(name = "DESCRIPTION_CATEGORY", nullable = false, insertable = true)
	@Lob
	private String descriptionCategory;
	

	@Builder
	public Category(Instant createdAt, Instant updatedAt, Boolean isActive, Long codeCategory,
			String codeUniqueCategory, String libelleCategory, String descriptionCategory,
			Collection<Product> products) {
		super(createdAt, updatedAt, isActive);
		this.codeCategory = codeCategory;
		this.codeUniqueCategory = codeUniqueCategory;
		this.libelleCategory = libelleCategory;
		this.descriptionCategory = descriptionCategory;
	   
	}

	 
	
	
	
	 

	
}
