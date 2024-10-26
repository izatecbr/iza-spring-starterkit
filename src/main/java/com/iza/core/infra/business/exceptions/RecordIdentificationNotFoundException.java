package com.iza.core.infra.business.exceptions;

import com.iza.core.infra.business.BusinessException;
import com.iza.core.infra.business.BusinessMessage;

public class RecordIdentificationNotFoundException extends BusinessException {
    public RecordIdentificationNotFoundException(String field, Object id) {
        super(BusinessMessage.E101, field, id);
    }
}
