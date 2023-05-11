package com.hrp.exception;
import lombok.Getter;

@Getter
public class CompanyException extends RuntimeException{
    private final EErrorType EErrorType;

    public CompanyException(EErrorType EErrorType){
        super(EErrorType.getMessage());
        this.EErrorType = EErrorType;
    }


    public CompanyException(EErrorType EErrorType, String message){
        super(message);
        this.EErrorType = EErrorType;
    }


}