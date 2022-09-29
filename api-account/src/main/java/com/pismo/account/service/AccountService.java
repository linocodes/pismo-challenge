package com.pismo.account.service;

import com.pismo.account.entity.AccountEntity;
import com.pismo.account.repository.AccountRepository;
import com.pismo.lib.constants.Constants;
import com.pismo.lib.exception.ElementNotFoundException;
import com.pismo.lib.model.request.AccountRequest;
import com.pismo.lib.model.request.UpdateAccountRequest;
import com.pismo.lib.model.response.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public void createAccount(AccountRequest request) {
        validateFields(request);
        repository.save(AccountEntity
                .builder()
                .documentNumber(request.getDocumentNumber())
                .build()
        );
    }

    public AccountResponse putAccountId(Long accountId, UpdateAccountRequest request) {
        AccountEntity accountEntity = findbyId(accountId);
        accountEntity.setSaldo(request.getSaldo());
        repository.save(accountEntity);
        return getAccountResponse(accountEntity);
    }

    public AccountResponse getAccountById(Long accountId) {
        AccountEntity accountEntity = findbyId(accountId);
        return getAccountResponse(accountEntity);
    }

    private AccountResponse getAccountResponse(AccountEntity accountEntity) {
        return AccountResponse.builder()
                .id(accountEntity.getId())
                .documentNumber(accountEntity.getDocumentNumber())
                .saldo(accountEntity.getSaldo())
                .build();
    }

    private AccountEntity findbyId(Long accountId) {
        return repository.findById(accountId)
                .orElseThrow(() -> new ElementNotFoundException(String.format(Constants.Messages.ACOOUNT_ID_NOT_FOUND, accountId)));
    }

    private void validateFields(AccountRequest request) {
        if (request == null || request.getDocumentNumber() == null) {
            throw new RuntimeException(String.format(Constants.Messages.DOCUMENT_NUMBER_EMPTY));
        }
    }

}
