package com.hrp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EErrorType {

    AUTH_PARAMETRE_ERROR(1201,"You have entered an invalid parameter",BAD_REQUEST),
    AUTH_PASSWORD_ERROR(1301,"Passwords do not match",BAD_REQUEST),
    AUTH_USERNAME_ERROR(1302,"Username already registered",BAD_REQUEST),
    INTERNAL_ERROR(3000,"Unexpected error on server",INTERNAL_SERVER_ERROR),
    INVALID_TOKEN(4001,"Invalid token information",BAD_REQUEST),
    BAD_REQUEST_ERROR(1202,"You have entered an invalid parameter",BAD_REQUEST),

    KULLANICI_BULUNAMADI(2301,"The user you were looking for could not be found",INTERNAL_SERVER_ERROR);
    private int code;
    private String message;
    private HttpStatus httpStatus;
}
