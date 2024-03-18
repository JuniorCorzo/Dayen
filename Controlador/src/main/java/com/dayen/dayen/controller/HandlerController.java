package com.dayen.dayen.controller;

import com.dayen.dayen.dao.response.ExceptionResponse;
import com.dayen.dayen.exceptions.lote.LoteNotExists;
import com.dayen.dayen.exceptions.personal.PersonalNotExists;
import com.dayen.dayen.exceptions.proceso.ProcesoNotExists;
import com.dayen.dayen.exceptions.tipoProceso.TipoProcesoNotExists;
import com.dayen.dayen.exceptions.usuario.CredentialsNotValid;
import com.dayen.dayen.exceptions.usuario.UserExists;
import com.dayen.dayen.exceptions.usuario.UserNotExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerController {
	@ExceptionHandler(UserNotExists.class)
	public ResponseEntity<ExceptionResponse> exceptionResponse(UserNotExists e){
		return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(UserExists.class)
	public ResponseEntity<ExceptionResponse> exceptionResponse(UserExists e){
		return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), HttpStatus.CONFLICT), HttpStatus.CONFLICT);
	}
	@ExceptionHandler(CredentialsNotValid.class)
	public ResponseEntity<ExceptionResponse> exceptionResponse(CredentialsNotValid e){
		return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(ProcesoNotExists.class)
	public ResponseEntity<ExceptionResponse> exceptionResponse(ProcesoNotExists e){
		return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(TipoProcesoNotExists.class)
	public ResponseEntity<ExceptionResponse> exceptionResponse(TipoProcesoNotExists e){
		return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(LoteNotExists.class)
	public ResponseEntity<ExceptionResponse> exceptionResponse(LoteNotExists e){
		return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(PersonalNotExists.class)
	public ResponseEntity<ExceptionResponse> exceptionResponse(PersonalNotExists e){
		return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
	}

}
