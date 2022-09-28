package com.pismo.transaction.service;


import com.pismo.lib.model.enums.SignalEnum;
import com.pismo.lib.model.enums.TypesOperationEnum;

import java.math.BigDecimal;

public interface TypeOperationInterface {

    default BigDecimal defineSignal(BigDecimal amount, TypesOperationEnum typeOperation) {
        if (typeOperation.getSignal().equals(SignalEnum.MINUS)) {
            return amount.negate();
        }
        return amount;
    }

}