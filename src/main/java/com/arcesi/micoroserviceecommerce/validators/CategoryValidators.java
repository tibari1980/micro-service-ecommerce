package com.arcesi.micoroserviceecommerce.validators;

import java.util.ArrayList;
import java.util.List;

import com.arcesi.micoroserviceecommerce.dtos.CategoryDTO;

import io.micrometer.common.util.StringUtils;

public class CategoryValidators {

	public static List<String> validate(CategoryDTO categoryDTO) {
		List<String> errors=new ArrayList<String>();
		if(null==categoryDTO) {
			errors.add(IConstantErrorsEnemuration.CATEGORY_NOT_VALIDE_CAHMPS_OBLIGATOIRE.getCode());
		}
		if(null!=categoryDTO) {
			//Validation Libelle
			if(StringUtils.isEmpty(categoryDTO.getLibelleCategory())) {
				errors.add(IConstantErrorsEnemuration.CATEGORY_NOT_VALIDE_LIBELLE_OBLIGATOIRE.getCode());
			}else if(StringUtils.isNotEmpty(categoryDTO.getLibelleCategory())) {
				if(categoryDTO.getLibelleCategory().length()<5 || categoryDTO.getLibelleCategory().length()>40) {
					errors.add(IConstantErrorsEnemuration.CATEGORY_NOT_VALIDE_LIBELLE_LENGTH.getCode());
				}
			}
			//Validation Description
			
			if(StringUtils.isEmpty(categoryDTO.getDescriptionCategory())) {
				errors.add(IConstantErrorsEnemuration.CATEGORY_NOT_VALIDE_DESCRIPTION_OBLIGATOIRE.getCode());
			}else if(StringUtils.isNotEmpty(categoryDTO.getDescriptionCategory())) {
				if(categoryDTO.getDescriptionCategory().length()<10) {
					errors.add(IConstantErrorsEnemuration.CATEGORY_NOT_VALIDE_DESCRIPTION_LENGTH.getCode());
				}
			}
		}
		return errors;
	}

}
