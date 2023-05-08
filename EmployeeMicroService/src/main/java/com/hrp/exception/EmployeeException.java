package com.hrp.exception;
import lombok.Getter;

@Getter
public class EmployeeException extends RuntimeException{
    private final EErrorType EErrorType;

    public EmployeeException(EErrorType EErrorType){
        super(EErrorType.getMessage());
        this.EErrorType = EErrorType;
    }


    public EmployeeException(EErrorType EErrorType, String message){
        super(message);
        this.EErrorType = EErrorType;
    }


}