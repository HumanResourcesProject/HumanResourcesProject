package com.hrp.exception;
import lombok.Getter;

@Getter
public class ManagerException extends RuntimeException{
    private final EErrorType EErrorType;

    public ManagerException(EErrorType EErrorType){
        super(EErrorType.getMessage());
        this.EErrorType = EErrorType;
    }


    public ManagerException(EErrorType EErrorType, String message){
        super(message);
        this.EErrorType = EErrorType;
    }


}