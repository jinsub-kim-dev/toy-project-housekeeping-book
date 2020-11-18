package com.jinsub.housekeeping.api.controller;

import com.jinsub.housekeeping.api.transaction.model.dto.CreateTransactionRequestDto;
import com.jinsub.housekeeping.api.transaction.model.dto.CreateTransactionResponseDto;
import com.jinsub.housekeeping.api.transaction.model.dto.ReadTransactionResponseDto;
import com.jinsub.housekeeping.api.transaction.model.dto.TransactionDto;
import com.jinsub.housekeeping.api.transaction.model.entity.Transaction;
import com.jinsub.housekeeping.api.transaction.service.TransactionReadService;
import com.jinsub.housekeeping.api.transaction.service.TransactionService;
import com.jinsub.housekeeping.base.model.CodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    TransactionReadService transactionReadService;

    @PostMapping({"", "/"})
    @ResponseBody
    public CodeResponse createTransaction(@RequestBody CreateTransactionRequestDto request) {
        Transaction createdTransaction = transactionService.createTransaction(request.getUserId(), request.getTransactionType(), request.getTransactionDate(),
                request.getAssetType(), request.getCategoryId(), request.getAmountOfMoney(), request.getDetails());
        TransactionDto transactionDto = TransactionDto.of(createdTransaction);
        CreateTransactionResponseDto response = CreateTransactionResponseDto.builder().transactionDto(transactionDto).build();
        return CodeResponse.successResult(response);
    }

    @GetMapping("/id")
    @ResponseBody
    public CodeResponse readTransactionById(@RequestParam long transactionId) {
        Transaction readTransaction = transactionReadService.getTransactionById(transactionId);
        TransactionDto transactionDto = TransactionDto.of(readTransaction);
        ReadTransactionResponseDto response = ReadTransactionResponseDto.builder().transactionDto(transactionDto).build();
        return CodeResponse.successResult(response);
    }
}
