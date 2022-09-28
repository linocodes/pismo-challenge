package com.pismo.transaction.service;

import com.pismo.lib.constants.Constants;
import com.pismo.lib.exception.BadRequestException;
import com.pismo.lib.exception.ElementNotFoundException;
import com.pismo.transaction.service.impl.CompraParceladaService;
import com.pismo.transaction.service.impl.PaymentService;
import com.pismo.transaction.service.impl.PurchaseService;
import com.pismo.transaction.service.impl.SaqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TypeOperationFactory {

    private final PurchaseService purchaseService;
    private final CompraParceladaService compraParceladaService;
    private final PaymentService paymentService;
    private final SaqueService saqueService;

    public TypeOperationInterface createTypeOperation(int typeOperation) {

        if (Objects.isNull(typeOperation)) {
            throw new BadRequestException("The type of operation is required.");
        }

        TypeOperationInterface service;
        switch (typeOperation) {
            case 1:
                service = purchaseService;
                break;
            case 2:
                service = compraParceladaService;
                break;
            case 3:
                service = saqueService;
                break;
            case 4:
                service = paymentService;
                break;
            default:
                throw new ElementNotFoundException(String.format(Constants.Messages.TYPE_OPERATION_ENUM_NOT_FOUND, typeOperation));
        }

        return service;

    }
}