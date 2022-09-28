package com.pismo.lib.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pismo.lib.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest implements Serializable {

    @NotBlank(message = Constants.Messages.DOCUMENT_NUMBER_EMPTY)
    @JsonProperty("document_number")
    private String documentNumber;

}
