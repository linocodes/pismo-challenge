package com.pismo.transaction.client;

import com.pismo.lib.model.request.UpdateAccountRequest;
import com.pismo.lib.model.response.AccountResponse;
import com.pismo.transaction.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "accountFeignClient",
        url = "${client.apiAccount.baseUrl}",
        configuration = FeignClientConfig.class)
public interface AccountFeignClient {

    @GetMapping("/v1/accounts/{accountId}")
    ResponseEntity<AccountResponse> getAccount(@PathVariable Long accountId);

    @PutMapping("/v1/accounts/{accountId}")
    ResponseEntity<AccountResponse> updateAccount(@PathVariable Long accountId, @RequestBody UpdateAccountRequest updateAccountRequest);

}