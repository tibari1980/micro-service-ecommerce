package com.arcesi.micoroserviceecommerce.exceptions.handlers;

import java.util.List;

import com.arcesi.micoroserviceecommerce.exceptions.enums.ErrorsCodesEnemuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorsDTO {

	private Integer httpStatus;
	private List<String> errors;
	private String message;
	private ErrorsCodesEnemuration code;
	
	
}
