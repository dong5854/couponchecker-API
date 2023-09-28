package com.dong.couponchecker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unexpected Error Occurred")
public class UnexpectedException extends Exception { }
