package com.pismo.account.controller;


import com.pismo.account.service.AccountService;
import com.pismo.lib.model.request.AccountRequest;
import com.pismo.lib.model.request.UpdateAccountRequest;
import com.pismo.lib.model.response.AccountResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/accounts")
@Tag(name = "Account API", description = "V1 Account - documentation")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @Operation(summary = "Create a new account", description = "Creates a new account as per the request body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "The document number is not valid"),
            @ApiResponse(responseCode = "500", description = "Something went wrong")
    })
    @PostMapping
    public ResponseEntity createAccount(@Valid @RequestBody AccountRequest request) {
        service.createAccount(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get a account by accountId", description = "Returns a account as per the accountId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "The account was not found")
    })
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponse> getAccountById(
            @PathVariable(value = "accountId")
            @Parameter(name = "accountId", description = "Account id to be searched", required = true) Long accountId) {
        return new ResponseEntity<AccountResponse>(service.getAccountById(accountId), HttpStatus.OK);
    }

    @Operation(summary = "Put a account by accountId", description = "Update account id as per the accountId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "The account was not found")
    })

    @PutMapping("/{accountId}")
    public ResponseEntity<AccountResponse> putAccount(
            @PathVariable(value = "accountId")
            @Parameter(name = "accountId", description = "Account id to be updated", required = true) Long accountId,
            @Valid @RequestBody UpdateAccountRequest request) {
        return new ResponseEntity<AccountResponse>(service.putAccountId(accountId, request), HttpStatus.OK);
    }

}