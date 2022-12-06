package com.stockAPI.eumsave;

import org.springframework.http.HttpStatus;

public enum TokenEnum {

	Token_SUCCESS(200, "tokem資訊取得成功", HttpStatus.OK),
	TOKEN_ERROR_EXPIRED(1000, "token驗證過期", HttpStatus.REQUEST_TIMEOUT), // httpstatus 408
	TOKEN_AUTH_FAIL(1001, "身分驗證發生錯誤", HttpStatus.FORBIDDEN);// httpstatus 403

	private Integer code;
	private String message;
	private HttpStatus httpstatus;

	private TokenEnum(Integer code, String message, HttpStatus httpstatus) {
		this.code = code;
		this.message = message;
		this.httpstatus = httpstatus;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpstatus() {
		return httpstatus;
	}

}
