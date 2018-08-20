package com.puc.tcc.entrega.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puc.tcc.entrega.dtos.BaseDTO;
import com.puc.tcc.entrega.exceptions.AvaliacaoException;
import com.puc.tcc.entrega.exceptions.EntregaException;
import com.puc.tcc.entrega.exceptions.GenericException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseDTO> processParameterizedValidationError(Exception ex) {
		log.error(ex.getMessage(),ex);
		return processError(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BaseDTO> processParameterizedValidationError(MethodArgumentNotValidException ex) {
		log.error(ex.getMessage(),ex);
		return processError(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ResponseBody
	@ExceptionHandler(EntregaException.class)
	public ResponseEntity<BaseDTO> processParameterizedValidationError(EntregaException ex) {
		log.error(ex.getMessage(),ex);
		return processError(ex.getMessage(),ex.getStatusCode());
	}
	
	@ResponseBody
	@ExceptionHandler(AvaliacaoException.class)
	public ResponseEntity<BaseDTO> processParameterizedValidationError(AvaliacaoException ex) {
		log.error(ex.getMessage(),ex);
		return processError(ex.getMessage(),ex.getStatusCode());
	}
	
	@ResponseBody
	@ExceptionHandler(GenericException.class)
	public ResponseEntity<BaseDTO> processParameterizedValidationError(GenericException ex) {
		log.error(ex.getMessage(),ex);
		return processError(ex.getMessage(),ex.getStatusCode());
	}

	private ResponseEntity<BaseDTO> processError(String error,HttpStatus headerStatus) {
		BaseDTO baseDTO = new BaseDTO(headerStatus.value(),error);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>(baseDTO,httpHeaders,headerStatus);
	}
}