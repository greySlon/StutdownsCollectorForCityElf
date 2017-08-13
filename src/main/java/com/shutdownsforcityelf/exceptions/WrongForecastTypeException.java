package com.shutdownsforcityelf.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Wrong forecast type")
public class WrongForecastTypeException extends Exception {

}
