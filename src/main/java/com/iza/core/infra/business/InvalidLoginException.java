package com.iza.core.infra.business;

public class InvalidLoginException extends BusinessException {
    public InvalidLoginException() {
        super(BusinessMessage.E110);
    }
}