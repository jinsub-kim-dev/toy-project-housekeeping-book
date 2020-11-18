package com.jinsub.housekeeping.api.controller;

import com.jinsub.housekeeping.api.transaction.model.dto.CreateTransactionRequestDto;
import com.jinsub.housekeeping.api.transaction.model.dto.CreateTransactionResponseDto;
import com.jinsub.housekeeping.api.transaction.model.dto.ReadTransactionResponseDto;
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
        Transaction transaction = transactionService.createTransaction(request.getUserId(), request.getTransactionType(), request.getTransactionDate(),
                request.getAssetType(), request.getCategoryId(), request.getAmountOfMoney(), request.getDetails());
        CreateTransactionResponseDto response = CreateTransactionResponseDto.of(transaction);
        return CodeResponse.successResult(response);
    }

    @GetMapping("/id")
    @ResponseBody
    public CodeResponse readTransactionById(@RequestParam long transactionId) {
        Transaction transaction = transactionReadService.getTransactionById(transactionId);
        ReadTransactionResponseDto response = ReadTransactionResponseDto.of(transaction);
        return CodeResponse.successResult(response);
    }
}
