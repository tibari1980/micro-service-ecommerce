package com.arcesi.micoroserviceecommerce.exceptions.enums;

public enum ErrorsCodesEnemuration {
	CATEGORY_NOT_FOUND(1000),
	CATEGORY_NOT_VALIDE(1001),
	PRODUCT_NOT_FOUND(2000),
	PRODUCT_NOT_VALIDE(2001);
	
	public final int code;
	
	private ErrorsCodesEnemuration(final int code) {
		this.code=code;
	}
	
	public int getCode() {
		return code;
	}

	
}
