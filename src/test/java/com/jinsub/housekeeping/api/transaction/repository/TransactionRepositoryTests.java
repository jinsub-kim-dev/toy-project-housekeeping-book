package com.jinsub.housekeeping.api.transaction.repository;

import com.jinsub.housekeeping.api.category.model.entity.Category;
import com.jinsub.housekeeping.api.transaction.enums.AssetType;
import com.jinsub.housekeeping.api.transaction.enums.TransactionType;
import com.jinsub.housekeeping.api.transaction.model.entity.Transaction;
import com.jinsub.housekeeping.api.user.model.entity.User;
import com.jinsub.housekeeping.base.helper.CryptoHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionRepositoryTests {

    @PersistenceContext
    EntityManager em;

    @Autowired
    TransactionRepository transactionRepository;

    @Test
    public void 거래내역을_생성한다() throws Exception {

        User testUser = createUser();
        TransactionType testTransactionType =  TransactionType.INCOME;
        LocalDateTime testTransactionDate = LocalDateTime.now();
        AssetType testAssetType = AssetType.CARD;
        Category testCategory = createCategory();
        long testAmountOfMoney = 1000000L;
        String testDetails = "test details";

        Transaction savedTransaction = transactionRepository.save(Transaction.builder()
                .user(testUser)
                .transactionType(testTransactionType)
                .transactionDate(testTransactionDate)
                .assetType(testAssetType)
                .category(testCategory)
                .amountOfMoney(testAmountOfMoney)
                .details(testDetails)
                .build());

        Transaction testTransaction = transactionRepository.findById(savedTransaction.getTransactionId()).get();

        assertThat(testTransaction.getUser().getUserName()).isEqualTo(testUser.getUserName());
        assertThat(testTransaction.getTransactionType()).isEqualTo(testTransactionType);
        assertThat(testTransaction.getTransactionDate()).isEqualTo(testTransactionDate);
        assertThat(testTransaction.getAssetType()).isEqualTo(testAssetType);
        assertThat(testTransaction.getCategory().getCategoryName()).isEqualTo(testCategory.getCategoryName());
        assertThat(testTransaction.getAmountOfMoney()).isEqualTo(testAmountOfMoney);
        assertThat(testTransaction.getDetails()).isEqualTo(testDetails);
    }

    private User createUser() throws Exception {
        User user = User.builder()
                .userName("test user name")
                .hashedEmail(CryptoHelper.getSha256HashedString("test@email.com"))
                .hashedPassword(CryptoHelper.getSha256HashedString("test password"))
                .build();

        em.persist(user);
        return user;
    }

    private Category createCategory() {
        Category category = Category.builder()
                .categoryName("test category name")
                .transactionType(TransactionType.INCOME)
                .common(true)
                .build();

        em.persist(category);
        return category;
    }
}
