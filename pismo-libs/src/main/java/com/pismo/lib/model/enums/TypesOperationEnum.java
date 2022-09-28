package com.pismo.lib.model.enums;

import com.pismo.lib.constants.Constants;
import com.pismo.lib.exception.ElementNotFoundException;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum TypesOperationEnum {

    COMPRA_A_VISTA(1, "Compra a vista", SignalEnum.MINUS),
    COMPRA_PARCELADA(2, "Compra parcelada", SignalEnum.MINUS),
    SAQUE(3, "Saque", SignalEnum.MINUS),
    PAGAMENTO(4, "Pagamento", SignalEnum.PLUS);

    private int operation;
    private String description;
    private SignalEnum signal;

    TypesOperationEnum(int operation, String description, SignalEnum signal) {
        this.operation = operation;
        this.description = description;
        this.signal = signal;
    }

    public static Stream<TypesOperationEnum> stream() {
        return Stream.of(TypesOperationEnum.values());
    }

    public static TypesOperationEnum getTypeOperation(int operation) {
        return stream().filter(i -> i.operation == operation)
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException(String.format(Constants.Messages.TYPE_OPERATION_ENUM_NOT_FOUND, operation)));
    }

}
