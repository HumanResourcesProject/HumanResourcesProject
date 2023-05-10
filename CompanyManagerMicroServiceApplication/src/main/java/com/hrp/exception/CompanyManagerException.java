package com.hrp.exception;
import lombok.Getter;

@Getter
public class CompanyManagerException extends RuntimeException{
    private final EErrorType EErrorType;

    public CompanyManagerException(EErrorType EErrorType){
        super(EErrorType.getMessage());
        this.EErrorType = EErrorType;
    }


    public CompanyManagerException(EErrorType EErrorType, String message){
        super(message);
        this.EErrorType = EErrorType;
    }


}