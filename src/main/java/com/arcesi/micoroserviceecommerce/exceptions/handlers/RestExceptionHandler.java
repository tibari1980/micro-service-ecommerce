package com.arcesi.micoroserviceecommerce.exceptions.handlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.arcesi.micoroserviceecommerce.exceptions.EntityNotFoundException;
import com.arcesi.micoroserviceecommerce.exceptions.InvalidEntityException;
/**
 * 
 * @author tibari
 * Ingénieur développement
 */
@Order(Ordered.LOWEST_PRECEDENCE)
@RestControllerAdvice

public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorsDTO> handleException(final EntityNotFoundException exception,final WebRequest request)
	{
		HttpStatus notFound=HttpStatus.NOT_FOUND;
		ErrorsDTO dto=ErrorsDTO.builder()
				.code(exception.getErrosCode())
				.httpStatus(notFound.value())
				.message(exception.getMessage())
				.build();
		return new ResponseEntity<ErrorsDTO>(dto,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(InvalidEntityException.class)
	public ResponseEntity<ErrorsDTO> handleException(final InvalidEntityException exception, final WebRequest request)
	{
		HttpStatus batRequest=HttpStatus.BAD_REQUEST;
		ErrorsDTO dto=ErrorsDTO.builder()
				.code(exception.getCodesErrorsEnemuration())
				.httpStatus(batRequest.value())
				.message(exception.getMessage())
				.errors(exception.getErrors())
				.build();
		return new ResponseEntity<ErrorsDTO>(dto,HttpStatus.BAD_REQUEST);
	}
	
}
