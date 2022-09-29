package com.pismo.lib.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponse {

    @JsonProperty("account_id")
    private Long id;

    @JsonProperty("document_number")
    private String documentNumber;

    @JsonProperty("available_credit_limit")
    private BigDecimal saldo;

}
