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
import com.jinsub.housekeeping.base.exception.model.HouseKeepingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionReadServiceTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionReadService transactionReadService;

    @Test
    public void Id_트랜잭션을_조회한다() {
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

        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.EXPENSE)
                .transactionDate(LocalDateTime.now())
                .assetType(AssetType.CASH)
                .amountOfMoney(1000)
                .details("test details")
                .build();
        transaction.setUser(user);
        transaction.setCategory(category);

        Transaction savedTransaction = transactionRepository.save(transaction);

        Transaction testTransaction = transactionReadService.getTransactionById(savedTransaction.getTransactionId());

        assertThat(testTransaction.getUser().getUserId()).isEqualTo(user.getUserId());
        assertThat(testTransaction.getTransactionType()).isEqualTo(savedTransaction.getTransactionType());
        assertThat(testTransaction.getAssetType()).isEqualTo(savedTransaction.getAssetType());
        assertThat(testTransaction.getCategory().getCategoryId()).isEqualTo(category.getCategoryId());
        assertThat(testTransaction.getAmountOfMoney()).isEqualTo(savedTransaction.getAmountOfMoney());
        assertThat(testTransaction.getDetails()).isEqualTo(savedTransaction.getDetails());
        assertThat(testTransaction.getUser().getTransactionList().size()).isGreaterThan(0);
        assertThat(testTransaction.getCategory().getTransactionList().size()).isGreaterThan(0);
    }

    @Test(expected = HouseKeepingException.class)
    public void 존재하지_않는_트랜잭션Id로_조회하는_경우() {
        final long INVALID_TRANSACTION_ID = -1;
        Transaction transaction = transactionReadService.getTransactionById(INVALID_TRANSACTION_ID);
    }
}
