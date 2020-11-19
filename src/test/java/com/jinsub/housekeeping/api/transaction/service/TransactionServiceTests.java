package com.jinsub.housekeeping.api.transaction.service;

import com.jinsub.housekeeping.api.category.enums.CategoryType;
import com.jinsub.housekeeping.api.category.model.entity.Category;
import com.jinsub.housekeeping.api.category.repository.CategoryRepository;
import com.jinsub.housekeeping.api.transaction.enums.AssetType;
import com.jinsub.housekeeping.api.transaction.enums.TransactionType;
import com.jinsub.housekeeping.api.transaction.model.entity.Transaction;
import com.jinsub.housekeeping.api.transaction.repository.TransactionRepository;
import com.jinsub.housekeeping.api.user.model.entity.User;
import com.jinsub.housekeeping.api.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@DataJpaTest
public class TransactionServiceTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionService transactionService;

    @Test
    public void 트랜잭션을_생성한다() {
        User user = userRepository.save(User.builder()
                .userName("test user name")
                .hashedEmail("test hashed email")
                .hashedPassword("test hashed password")
                .build());

        Category category = categoryRepository.save(Category.builder()
                .categoryName("test category name")
                .transactionType(TransactionType.EXPENSE)
                .categoryType(CategoryType.COMMON)
                .build());

        Transaction savedTransaction = transactionService.createTransaction(user.getUserId(),
                TransactionType.EXPENSE, LocalDateTime.now(), AssetType.CASH, category.getCategoryId(),
                1000, "test details");

        Transaction testTransaction = transactionRepository.findById(savedTransaction.getTransactionId()).get();

        assertThat(testTransaction.getUser().getUserId()).isEqualTo(user.getUserId());
        assertThat(testTransaction.getTransactionType()).isEqualTo(savedTransaction.getTransactionType());
        assertThat(testTransaction.getAssetType()).isEqualTo(savedTransaction.getAssetType());
        assertThat(testTransaction.getCategory().getCategoryId()).isEqualTo(category.getCategoryId());
        assertThat(testTransaction.getAmountOfMoney()).isEqualTo(savedTransaction.getAmountOfMoney());
        assertThat(testTransaction.getDetails()).isEqualTo(savedTransaction.getDetails());
        assertThat(user.getTransactionList().size()).isGreaterThan(0);
        assertThat(category.getTransactionList().size()).isGreaterThan(0);
    }

    @Test
    public void 트랜잭션을_수정한다() {
        User user = userRepository.save(User.builder()
                .userName("test user name")
                .hashedEmail("test hashed email")
                .hashedPassword("test hashed password")
                .build());

        Category category = categoryRepository.save(Category.builder()
                .categoryName("test category name")
                .transactionType(TransactionType.EXPENSE)
                .categoryType(CategoryType.COMMON)
                .build());

        Transaction savedTransaction = transactionService.createTransaction(user.getUserId(),
                TransactionType.EXPENSE, LocalDateTime.now(), AssetType.CASH, category.getCategoryId(),
                1000, "test details");

        TransactionType modifiedTransactionType = TransactionType.INCOME;
        LocalDateTime modifiedTransactionDate = savedTransaction.getTransactionDate().minusDays(1L);
        AssetType modifiedAssetType = AssetType.CARD;
        long modifiedAmountOfMoney = 2000L;
        String modifiedDetails = "modified details";

        transactionService.updateTransaction(savedTransaction.getTransactionId(), modifiedTransactionType,
                modifiedTransactionDate, modifiedAssetType, modifiedAmountOfMoney, modifiedDetails);

        Transaction testTransaction = transactionRepository.findById(savedTransaction.getTransactionId()).get();

        assertThat(testTransaction.getTransactionType()).isEqualTo(modifiedTransactionType);
        assertThat(testTransaction.getTransactionDate()).isEqualTo(modifiedTransactionDate);
        assertThat(testTransaction.getAssetType()).isEqualTo(modifiedAssetType);
        assertThat(testTransaction.getAmountOfMoney()).isEqualTo(modifiedAmountOfMoney);
        assertThat(testTransaction.getDetails()).isEqualTo(modifiedDetails);
    }
}
