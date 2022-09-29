package com.pismo.transaction.service;

import com.pismo.lib.constants.Constants;
import com.pismo.lib.exception.BadRequestException;
import com.pismo.lib.exception.InternalServerErrorException;
import com.pismo.lib.model.enums.TypesOperationEnum;
import com.pismo.lib.model.request.TransactionRequest;
import com.pismo.lib.model.request.UpdateAccountRequest;
import com.pismo.lib.model.response.AccountResponse;
import com.pismo.transaction.client.AccountFeignClient;
import com.pismo.transaction.entity.TransactiontEntity;
import com.pismo.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacionService {

    private final AccountFeignClient accountFeignClient;
    private final TypeOperationFactory typeOperationFactory;
    private final TransactionRepository transactionRepository;

    public void createTypeOperation(TransactionRequest request) {

        validateFields(request);
        TypeOperationInterface service = typeOperationFactory.createTypeOperation(request.getTypeOperation().intValue());
        createtransaction(request, service);

    }

    private void createtransaction(TransactionRequest request, TypeOperationInterface service) {

        AccountResponse accountResponse = getAccount(request.getAccountId());

        BigDecimal amount =  calculaNovoSaldo(request, service, accountResponse.getSaldo());

        try {
            TransactiontEntity transactiontEntity = TransactiontEntity.builder()
                    .accountId(request.getAccountId())
                    .typeOperation(request.getTypeOperation().intValue())
                    .amount(getAmount(request, service))
                    .build();

            transactionRepository.save(transactiontEntity);

            updateAccount(request.getAccountId(), amount);

        } catch (Exception e) {
            log.error("Ocorreu um erro: " + e.getMessage(), e);
            throw new InternalServerErrorException("Erro ao gravar.");
        }
    }

    public void updateAccount(Long accountId, BigDecimal amount) {
        UpdateAccountRequest updateAccountRequest = UpdateAccountRequest.builder().saldo(amount).build();
        AccountResponse  response = accountFeignClient.updateAccount(accountId, updateAccountRequest).getBody();
        log.info(response.toString());
    }

    public AccountResponse getAccount(Long accountId) {
        return accountFeignClient.getAccount(accountId).getBody();
    }

    private static BigDecimal getAmount(TransactionRequest request, TypeOperationInterface service) {
        return service.defineSignal(request.getAmount(), TypesOperationEnum.getTypeOperation(request.getTypeOperation().intValue()));
    }

    private BigDecimal calculaNovoSaldo(TransactionRequest request, TypeOperationInterface service, BigDecimal saldoAccount) {

        BigDecimal amount = saldoAccount.add(getAmount(request,service));

        if (amount.compareTo(new BigDecimal(0)) == -1) {
            throw new BadRequestException("Saldo não permite a operação.");
        }

        return amount;

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
