package com.arcesi.micoroserviceecommerce.exceptions;

import com.arcesi.micoroserviceecommerce.exceptions.enums.ErrorsCodesEnemuration;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @author tibari Zeroual 
 * Ingénieur développement 
 *
 */
@Getter @Setter
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ErrorsCodesEnemuration errosCode;

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public EntityNotFoundException(final String message, final Throwable cause,
			final ErrorsCodesEnemuration errosCode) {
		super(message, cause);
		this.errosCode = errosCode;

	}

	public EntityNotFoundException(String message) {
		super(message);

	}

	public EntityNotFoundException(String message, ErrorsCodesEnemuration errorsCodesEnemuration) {
		super(message);
		this.errosCode = errorsCodesEnemuration;
	}

}
