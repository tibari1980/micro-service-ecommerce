package com.arcesi.micoroserviceecommerce.validators;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.PDLOverrideSupported;

import org.apache.commons.lang.StringUtils;

import com.arcesi.micoroserviceecommerce.dtos.ProductDTO;
import com.arcesi.micoroserviceecommerce.utils.ControleSyntaxe;
import com.arcesi.micoroserviceecommerce.utils.Utils;

public class ProductValidators {

	public static List<String> validate(ProductDTO productDto) {
		List<String> errors=new ArrayList<>();
		if(null==productDto) {
			errors.add(IConstantErrorsEnemuration.PRODUCT_TOUS_CHAMPS_OBLIGATOIRE.getCode());
		}
		if(null!=productDto) {
			//validation Description
			if(StringUtils.isBlank(productDto.getDescription())) {
				errors.add(IConstantErrorsEnemuration.PRODUCT_NOT_VALIDE_DESCRIPTION_OBLIGATOIRE.getCode());
			}else if(productDto.getDescription().length()<5 || productDto.getDescription().length()>60) {
				errors.add(IConstantErrorsEnemuration.PRODUCT_NOT_VALIDE_DESCRIPTION_LENGTH.getCode());
			}
			
			if(StringUtils.isBlank(productDto.getDesignation())) {
				errors.add(IConstantErrorsEnemuration.PRODUCT_NOT_VALIDE_LIBELLE_OBLIGATOIRE.getCode());
			}else if(productDto.getDesignation().length()<5 || productDto.getDesignation().length()>60) {
				errors.add(IConstantErrorsEnemuration.PRODUCT_NOT_VALIDE_LIBELLE_LENGTH.getCode());
			}
			
			//validation prix unitaire
			if(productDto.getPrixUnitaire()<=0) {
				errors.add(IConstantErrorsEnemuration.PRODUCT_NOT_VALIDE_PRIX_UNITAIRE.getCode());
			}
			if(Utils.isBooleanFalse(ControleSyntaxe.isBigDecimal(String.valueOf(productDto.getPrixUnitaire())))){
				errors.add(IConstantErrorsEnemuration.PRODUCT_NOT_VALIDE_PRIXUNITAIRE.getCode());
			}
		}
		return errors;
	}

}
