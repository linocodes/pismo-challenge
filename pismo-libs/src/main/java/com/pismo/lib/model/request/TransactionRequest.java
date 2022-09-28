package com.pismo.lib.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pismo.lib.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"accountId", "typeOperation", "amount"})
public class TransactionRequest implements Serializable {

    @NotNull(message = Constants.Messages.ACOOUNT_ID_EMPTY)
    @JsonProperty("account_id")
    private Long accountId;

    @NotNull(message = Constants.Messages.TYPE_OPERATION_EMPTY)
    @JsonProperty("operation_type_id")
    private Long typeOperation;

    @NotNull(message = Constants.Messages.AMOUNT_EMPTY)
    private BigDecimal amount;

}
