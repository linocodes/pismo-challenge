package com.pismo.transaction.service;

import com.pismo.lib.constants.Constants;
import com.pismo.lib.exception.InternalServerErrorException;
import com.pismo.lib.model.enums.TypesOperationEnum;
import com.pismo.lib.model.request.TransactionRequest;
import com.pismo.transaction.entity.TransactiontEntity;
import com.pismo.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacionService {

    private final TypeOperationFactory typeOperationFactory;
    private final TransactionRepository repository;

    public void createTypeOperation(TransactionRequest request) {

        validateFields(request);
        TypeOperationInterface service = typeOperationFactory.createTypeOperation(request.getTypeOperation().intValue());
        createtransaction(request, service);

    }

    private void createtransaction(TransactionRequest request, TypeOperationInterface service) {
        try {
            TransactiontEntity transactiontEntity = TransactiontEntity.builder()
                    .accountId(request.getAccountId())
                    .typeOperation(request.getTypeOperation().intValue())
                    .amount(service.defineSignal(request.getAmount(), TypesOperationEnum.getTypeOperation(request.getTypeOperation().intValue())))
                    .build();

            repository.save(transactiontEntity);
        } catch (Exception e) {
            log.error("Ocorreu um erro: " + e.getMessage(), e);
            throw new InternalServerErrorException("Erro ao gravar.");
        }
    }

    private void validateFields(TransactionRequest request) {

        if (Objects.isNull(request.getAccountId())) {
            throw new RuntimeException(String.format(Constants.Messages.DOCUMENT_NUMBER_EMPTY));
        }

        if (Objects.isNull(request.getTypeOperation())) {
            throw new RuntimeException(String.format(Constants.Messages.TYPE_OPERATION_EMPTY));
        }

        if (Objects.isNull(request.getAmount())) {
            throw new RuntimeException(String.format(Constants.Messages.AMOUNT_EMPTY));
        }

    }

}
