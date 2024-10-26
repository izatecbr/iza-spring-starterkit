package com.iza.core.infra.business.exceptions;

import com.iza.core.infra.business.BusinessException;
import com.iza.core.infra.business.BusinessMessage;

public class RecordNotFoundException extends BusinessException {
    public RecordNotFoundException(String field, Object id) {
        super(BusinessMessage.E404, field, id);
    }
}
