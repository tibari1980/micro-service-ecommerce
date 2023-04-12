package com.arcesi.micoroserviceecommerce.exceptions;

import java.util.List;

import com.arcesi.micoroserviceecommerce.exceptions.enums.ErrorsCodesEnemuration;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author tibari ingénieur developpement
 */
@Getter
@Setter
//Classe pour gerer les exceptions d'une entite not valide
public class InvalidEntityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ErrorsCodesEnemuration codesErrorsEnemuration;

	/** Liste des errors **/
	private List<String> errors;

	public InvalidEntityException(String message, Throwable cause) {
		super(message, cause);

	}

	public InvalidEntityException(String message) {
		super(message);

	}

	public InvalidEntityException(String message, Throwable cause, final ErrorsCodesEnemuration codesEnemuration,
			final List<String> errors) {
		super(message, cause);
		this.codesErrorsEnemuration = codesEnemuration;
		this.errors = errors;
	}

	public InvalidEntityException(String message, Throwable cause, final ErrorsCodesEnemuration codesEnemuration) {
		super(message, cause);
		this.codesErrorsEnemuration = codesEnemuration;
	}

	public InvalidEntityException(String message, final ErrorsCodesEnemuration codesEnemuration) {
		super(message);
		this.codesErrorsEnemuration = codesEnemuration;
	}
}
