package com.pismo.lib.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pismo.lib.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountRequest implements Serializable {

    private BigDecimal saldo;

}
