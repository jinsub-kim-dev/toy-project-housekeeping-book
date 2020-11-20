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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
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

    @Test
    public void 유저의_모든_트랜잭션을_조회한다() {
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

        final int NUM_OF_TRANSACTION = 3;
        List<Long> testAmountOfMonetList = Arrays.asList(1000L, 5000L, 2000L);
        List<String> testDetailsList = Arrays.asList("test details 1", "test details 2", "test details 3");
        List<Transaction> transactionList = new ArrayList<>();
        for (int i = 0; i < NUM_OF_TRANSACTION; i++) {
            Transaction transaction = Transaction.builder()
                    .transactionType(TransactionType.EXPENSE)
                    .transactionDate(LocalDateTime.now())
                    .assetType(AssetType.CASH)
                    .amountOfMoney(testAmountOfMonetList.get(i))
                    .details(testDetailsList.get(i))
                    .build();
            transaction.setUser(user);
            transaction.setCategory(category);

            transactionList.add(transactionRepository.save(transaction));
        }

        List<Transaction> testTransactionList = transactionReadService.getUserTransactionList(user.getUserId());

        assertThat(testTransactionList.size()).isEqualTo(NUM_OF_TRANSACTION);
        for (int i = 0; i < NUM_OF_TRANSACTION; i++) {
            assertThat(testTransactionList.get(i).getAmountOfMoney()).isEqualTo(testAmountOfMonetList.get(i));
            assertThat(testTransactionList.get(i).getDetails()).isEqualTo(testDetailsList.get(i));
        }
    }
}
