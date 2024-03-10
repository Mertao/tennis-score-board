package com.mertao.tennisscoreboard.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.mertao.tennisscoreboard.dto.ExceptionInfoDTO;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(IndexOutOfBoundsException.class)
	public ResponseEntity<ExceptionInfoDTO> handleException(IndexOutOfBoundsException exception) {
		ExceptionInfoDTO info = new ExceptionInfoDTO();
		info.setInfo(exception.getMessage());
		return new ResponseEntity<>(info, HttpStatus.CONFLICT);
	}
}
