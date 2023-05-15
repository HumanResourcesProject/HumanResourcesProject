package com.hrp.exception;
import lombok.Getter;

@Getter
public class RequirementsMicroException extends RuntimeException{
    private final EErrorType EErrorType;

    public RequirementsMicroException(EErrorType EErrorType){
        super(EErrorType.getMessage());
        this.EErrorType = EErrorType;
    }


    public RequirementsMicroException(EErrorType EErrorType, String message){
        super(message);
        this.EErrorType = EErrorType;
    }


}