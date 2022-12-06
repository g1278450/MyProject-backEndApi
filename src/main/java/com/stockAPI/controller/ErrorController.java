package com.stockAPI.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController{
	
	 @RequestMapping("/error")
	  public void handleError(HttpServletRequest request) throws Throwable {
	//只要遇到exception就直接拋出
	if (request.getAttribute("javax.servlet.error.exception") != null) {
		throw (Throwable) request.getAttribute("javax.servlet.error.exception");
	}
}

}
