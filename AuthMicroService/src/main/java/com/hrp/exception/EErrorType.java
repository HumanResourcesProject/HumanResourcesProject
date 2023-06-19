package com.hrp.exception;

import com.cloudinary.api.exceptions.BadRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EErrorType {
    // server
    INTERNAL_ERROR(1000,"UNEXPECTED ERROR ON SERVER",INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(1001,"INVALID PARAMETER",BAD_REQUEST),


    // controller
    NAME_NOT_EMPTY(1112,"NAME CANNOT BE EMPTY",BAD_REQUEST),
    SURNAME_NOT_EMPTY(1113,"SURNAME CANNOT BE EMPTY",BAD_REQUEST),
    PHONE_NUMBER_NOT_EMPTY(1114,"PHONE NUMBER CANNOT BE EMPTY",BAD_REQUEST),
    PASSWORD_NOT_EMPTY(1115,"PASSWORD NOT EMPTY",BAD_REQUEST),

    // Service
    E_MAIL_NOT_EMPTY(1211,"E-MAIL ALREADY REGISTERED",BAD_REQUEST),
    INVALID_TOKEN(1212,"INVALID TOKEN",BAD_REQUEST),
    USER_NOT_FOUNT(1213,"ADMIN NOT FOUND",BAD_REQUEST),
    PASSWORD_NOT_MATCH(1214,"PASSWORD NOT MATCH",BAD_REQUEST),
    EMAIL_ALREADY_HAVE(1215,"EMAIL ALREADY HAVE",BAD_REQUEST);




    private int code;
    private String message;
    private HttpStatus httpStatus;
}
