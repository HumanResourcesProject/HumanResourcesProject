package com.hrp.exception;
import lombok.Getter;

@Getter
public class AdminException extends RuntimeException{
    private final EErrorType EErrorType;

    public AdminException(EErrorType EErrorType){
        super(EErrorType.getMessage());
        this.EErrorType = EErrorType;
    }

    public AdminException(EErrorType EErrorType, String message){
        super(message);
        this.EErrorType = EErrorType;
    }
}