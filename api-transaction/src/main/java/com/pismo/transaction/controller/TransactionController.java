package com.pismo.transaction.controller;

import com.pismo.lib.model.request.TransactionRequest;
import com.pismo.transaction.service.TransacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/transactions")
@Tag(name = "Transaction API", description = "V1 Transaction - documentation")
@RequiredArgsConstructor
public class TransactionController {

    private final TransacionService service;

    @Operation(summary = "Create a new account", description = "Creates a new account as per the request body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "The request is not valid"),
            @ApiResponse(responseCode = "500", description = "Something went wrong")
    })
    @PostMapping
    public ResponseEntity createTransaction(@Valid @RequestBody TransactionRequest request) {
        service.createTypeOperation(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}