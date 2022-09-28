package com.pismo.account;

import com.pismo.account.entity.AccountEntity;
import com.pismo.account.repository.AccountRepository;
import com.pismo.account.service.AccountService;
import com.pismo.lib.constants.Constants;
import com.pismo.lib.model.request.AccountRequest;
import com.pismo.lib.model.response.AccountResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private AccountService service;

    @Test
    public void testShouldCreateAccountWithSuccess() {
        AccountRequest accountRequest = AccountRequest.builder().documentNumber("12345678900").build();
        service.createAccount(accountRequest);
        verify(repository, times(1)).save(any());
    }

    @Test
    public void testShouldCreateAccountWithFailed() {
        AccountRequest accountRequest = AccountRequest.builder().build();
        Exception exception = assertThrows(Exception.class, () -> service.createAccount(accountRequest));
        assertEquals(Constants.Messages.DOCUMENT_NUMBER_EMPTY, exception.getMessage());
    }

    @Test
    public void testShouldGetAccountByIdWithSuccess() {
        AccountEntity accountEntity = AccountEntity
                .builder()
                .id(1L)
                .documentNumber("12345678900")
                .build();
        when(repository.findById(any())).thenReturn(Optional.ofNullable(accountEntity));
        AccountResponse accountResponse = service.getAccountById(any());
        verify(repository, times(1)).findById(any());
        assertEquals(accountResponse.getId(), accountEntity.getId());
        assertEquals(accountResponse.getDocumentNumber(), accountEntity.getDocumentNumber());
    }

    @Test
    public void testShouldGetAccountByIdWithFailed() {
        Optional<AccountEntity> accountEntity = Optional.empty();
        when(repository.findById(any())).thenReturn(accountEntity);
        Exception exception = assertThrows(Exception.class, () -> service.getAccountById(1L));
        assertEquals(String.format(Constants.Messages.ACOOUNT_ID_NOT_FOUND, 1L), exception.getMessage());
    }

}
