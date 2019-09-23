package com.capgemini.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DemoException {
	@ResponseBody
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = {Exception.class})
    protected ErrorInfo handleConflict(Exception ex, HttpServletRequest req) {
        String bodyOfResponse = ex.getMessage();
        String uri = req.getRequestURL().toString();
        return  new ErrorInfo(uri,bodyOfResponse);
    }
}
