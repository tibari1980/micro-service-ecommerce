package com.arcesi.micoroserviceecommerce.validators;

public enum IConstantErrorsEnemuration {

	CATEGORY_NOT_VALIDE_CAHMPS_OBLIGATOIRE("Veuillez saisir tous les champs "), 
	CATEGORY_NOT_VALIDE_LIBELLE_OBLIGATOIRE("Libelle ne peut pas être null !"),
	CATEGORY_NOT_VALIDE_LIBELLE_LENGTH("Libelle doit être supérieur à 5 caractères et inférieur à 40 caractères ."),
	CATEGORY_NOT_VALIDE_DESCRIPTION_OBLIGATOIRE("Description ne peut pas être null !!"),
	CATEGORY_NOT_VALIDE_DESCRIPTION_LENGTH("Description doit être supérieur à 10 caractères ."),
	PRODUCT_TOUS_CHAMPS_OBLIGATOIRE("Veuillez remplir tous les champs obligatoires"), PRODUCT_NOT_VALIDE_DESCRIPTION_OBLIGATOIRE("Champs description ne peut pas être null"),
	PRODUCT_NOT_VALIDE_DESCRIPTION_LENGTH("Descrition doit être supérieur à 5 caractères ."),
	PRODUCT_NOT_VALIDE_LIBELLE_OBLIGATOIRE("Designation ne doit pas être vide!!"), PRODUCT_NOT_VALIDE_LIBELLE_LENGTH("Desgination doit être supérieur à 5 caractères et inférieur à 60 caractères !!!!"),
	PRODUCT_NOT_VALIDE_PRIX_UNITAIRE("Prix untiarie doit être supérieur à 0"),
	PRODUCT_NOT_VALIDE_PRIXUNITAIRE_FORMAT("Prix Unitaire non valide ex : 154.47"),
	PRODUCT_NOT_VALIDE_QUANTITESTOCK("Quantité en stock doit être supérieur à 0 !!"),
	PRODUCT_NOT_VALIDE_QUANTITESTOCK_FORMAT("Quantité en stock non valide ex : 500 ou 140 "),
	PRODUCT_NOT_VALIDE_ISDISPONIBLE_OBLIGATOIRE("Champs isDisponible est obligatoire true ou false"),
	PRODUCT_NOT_VALIDE_IMAGE_FORMAT("Champs image non valide"),
	PRODUCT_NOT_VALIDE_ISPROMOTION_OBLIGATOIRE("Champs isEnPromotion est obligatoire true ou false");
	public final String code;
	private IConstantErrorsEnemuration(final String code) {
		this.code=code;
	}
	
	public String getCode() {
		return code;
	}
}
