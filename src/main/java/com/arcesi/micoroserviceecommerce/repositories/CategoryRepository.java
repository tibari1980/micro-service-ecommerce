package com.arcesi.micoroserviceecommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.arcesi.micoroserviceecommerce.entities.Category;



public interface CategoryRepository extends JpaRepository<Category,Long> {

	Page<Category> findByLibelleCategoryContaining(String libelle, Pageable pageable);

	Category findByCodeUniqueCategory(String codeUnique);

	Page<Category> findByIsActive(boolean b, Pageable pageable);

	Category findByLibelleCategoryIgnoreCase(String libelleCategory);

}
