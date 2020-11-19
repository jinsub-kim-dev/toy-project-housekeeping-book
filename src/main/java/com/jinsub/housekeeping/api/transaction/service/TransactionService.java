package com.jinsub.housekeeping.api.transaction.service;

import com.jinsub.housekeeping.api.category.model.entity.Category;
import com.jinsub.housekeeping.api.category.service.CategoryReadService;
import com.jinsub.housekeeping.api.transaction.enums.AssetType;
import com.jinsub.housekeeping.api.transaction.enums.TransactionType;
import com.jinsub.housekeeping.api.transaction.model.entity.Transaction;
import com.jinsub.housekeeping.api.transaction.repository.TransactionRepository;
import com.jinsub.housekeeping.api.user.model.entity.User;
import com.jinsub.housekeeping.api.user.service.UserReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    // Repositories
    @Autowired
    TransactionRepository transactionRepository;

    // Services
    @Autowired
    TransactionReadService transactionReadService;
    @Autowired
    UserReadService userReadService;
    @Autowired
    CategoryReadService categoryReadService;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Transaction createTransaction(
            long userId,
            TransactionType transactionType,
            LocalDateTime transactionDate,
            AssetType assetType,
            long categoryId,
            long amountOfMoney,
            String details) {

        User user = userReadService.getUserById(userId);
        Category category = categoryReadService.getCategoryById(categoryId);

        Transaction transaction = Transaction.builder()
                .transactionType(transactionType)
                .transactionDate(transactionDate)
                .assetType(assetType)
                .category(category)
                .amountOfMoney(amountOfMoney)
                .details(details)
                .build();
        transaction.setUser(user);
        transaction.setCategory(category);

        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(long transactionId,
                                         TransactionType transactionType,
                                         LocalDateTime transactionDate,
                                         AssetType assetType,
                                         long amountOfMoney,
                                         String details) {

        Transaction transaction = transactionReadService.getTransactionById(transactionId);
        transaction.modifyTransactionType(transactionType);
        transaction.modifyTransactionDate(transactionDate);
        transaction.modifyAssetType(assetType);
        transaction.modifyAmountOfMoney(amountOfMoney);
        transaction.modifyDetails(details);
        return transaction;
    }
}
