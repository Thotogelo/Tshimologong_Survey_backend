package com.surver.web.exceptions;

public interface ExceptionDistributer {

    static ExceptionDTO distributeException(String message){
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(message);
        return exceptionDTO;
    }
}
