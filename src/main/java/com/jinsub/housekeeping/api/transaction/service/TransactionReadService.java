package com.jinsub.housekeeping.api.transaction.service;

import com.jinsub.housekeeping.api.transaction.model.entity.Transaction;
import com.jinsub.housekeeping.api.transaction.repository.TransactionRepository;
import com.jinsub.housekeeping.api.user.model.entity.User;
import com.jinsub.housekeeping.api.user.service.UserReadService;
import com.jinsub.housekeeping.base.exception.enums.HouseKeepingErrorType;
import com.jinsub.housekeeping.base.exception.model.HouseKeepingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionReadService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserReadService userReadService;

    public Transaction getTransactionById(long transactionId) {
        return transactionRepository.findById(transactionId).orElseThrow(
                () -> new HouseKeepingException(HouseKeepingErrorType.POLICY_VIOLATION_NOT_EXIST_TRANSACTION, "transation is not exist"));
    }

    public List<Transaction> getUserTransactionList(long userId) {
        User user = userReadService.getUserById(userId);
        return user.getTransactionList();
    }
}
