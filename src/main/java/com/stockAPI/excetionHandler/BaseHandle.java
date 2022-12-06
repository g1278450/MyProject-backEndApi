package com.stockAPI.excetionHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stockAPI.eumsave.TokenEnum;
import com.stockAPI.model.ReturnMessage;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class BaseHandle {
	
	static Logger logger = LogManager.getLogger();

	// 登入驗證錯誤
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ReturnMessage> usernameNotFoundException(UsernameNotFoundException e) {
		ReturnMessage messages = new ReturnMessage();
		messages.setMessage(e.getMessage());
		return new ResponseEntity<>(messages, HttpStatus.FORBIDDEN);
	}

	// token過期
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<ReturnMessage> expiredJwtException(ExpiredJwtException e) {
		ReturnMessage messages = new ReturnMessage();
		messages.setMessage(TokenEnum.TOKEN_ERROR_EXPIRED.getMessage());
		return new ResponseEntity<>(messages, HttpStatus.REQUEST_TIMEOUT);
	}

	// 身分驗證有誤
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ReturnMessage> AuthenticationException(AuthenticationException e) {
		logger.error(e.getMessage());
		ReturnMessage messages = new ReturnMessage();
		messages.setMessage(TokenEnum.TOKEN_AUTH_FAIL.getMessage());
		return new ResponseEntity<>(messages,TokenEnum.TOKEN_AUTH_FAIL.getHttpstatus()); 
	}
}
