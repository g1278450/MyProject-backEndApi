package com.stockAPI.eumsave;

import org.springframework.http.HttpStatus;

public enum TokenEnum {

	TOKEN_SUCCESS(200, "token_取得成功", HttpStatus.OK),
	TOKEN_AUTH_FAIL(403, "身分驗證發生錯誤", HttpStatus.FORBIDDEN),// httpstatus 403
	TOKEN_ERROR_EXPIRED(408, "token_驗證過期", HttpStatus.REQUEST_TIMEOUT); // httpstatus 408

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
