package com.arcesi.micoroserviceecommerce.validators;

public enum IConstantErrorsEnemuration {

	CATEGORY_NOT_VALIDE_CAHMPS_OBLIGATOIRE("Veuillez saisir tous les champs "), 
	CATEGORY_NOT_VALIDE_LIBELLE_OBLIGATOIRE("Libelle ne peut pas être null !"),
	CATEGORY_NOT_VALIDE_LIBELLE_LENGTH("Libelle doit être supérieur à 5 caractères et inférieur à 40 caractères ."),
	CATEGORY_NOT_VALIDE_DESCRIPTION_OBLIGATOIRE("Description ne peut pas être null !!"),
	CATEGORY_NOT_VALIDE_DESCRIPTION_LENGTH("Description doit être supérieur à 10 caractères .");
	public final String code;
	private IConstantErrorsEnemuration(final String code) {
		this.code=code;
	}
	
	public String getCode() {
		return code;
	}
}
