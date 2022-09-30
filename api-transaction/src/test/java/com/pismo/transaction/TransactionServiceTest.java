package com.pismo.transaction;


import com.pismo.lib.constants.Constants;
import com.pismo.transaction.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService service;

    @Test
    void testCalculoNovoSaldoWithOperacaoPagamentoSucess() throws Exception {
        BigDecimal saldoAccount = new BigDecimal(100);
        BigDecimal amountOperation = new BigDecimal(100);
        BigDecimal amount = service.calculaNovoSaldo(amountOperation, saldoAccount);
        assertEquals(amount, saldoAccount.add(amountOperation));
    }

    @Test
    void testCalculoNovoSaldoWithOperacaoSaqueSucess() throws Exception {
        BigDecimal saldoAccount = new BigDecimal(100);
        BigDecimal amountOperation = new BigDecimal(100).negate();
        BigDecimal amount = service.calculaNovoSaldo(amountOperation, saldoAccount);
        assertEquals(amount, saldoAccount.add(amountOperation));
    }

    @Test
    void testCalculoNovoSaldoWithOperacaoSaqueFailed() throws Exception {
        BigDecimal saldoAccount = new BigDecimal(50);
        BigDecimal amountOperation = new BigDecimal(100).negate();
        Exception exception = assertThrows(Exception.class, () -> service.calculaNovoSaldo(amountOperation, saldoAccount));
        assertEquals(String.format(Constants.Messages.SALDO_NEGATIVO), exception.getMessage());
    }

}
