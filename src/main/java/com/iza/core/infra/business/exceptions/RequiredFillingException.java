package com.iza.core.infra.business.exceptions;

import com.iza.core.infra.business.BusinessException;
import com.iza.core.infra.business.BusinessMessage;

public class RequiredFillingException extends BusinessException {
    public RequiredFillingException(String field) {
        super(BusinessMessage.E100, field);
    }
}
