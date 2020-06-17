package com.jinsub.housekeeping.api.category.repository;

import com.jinsub.housekeeping.api.category.model.entity.Category;
import com.jinsub.housekeeping.api.transaction.enums.TransactionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTests {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void 카테고리를_생성한다() {

        String testCategoryName = "test category name";
        TransactionType testTransactionType = TransactionType.EXPENSE;
        boolean testCommon = true;

        Category savedCategory = categoryRepository.save(Category.builder()
                .categoryName(testCategoryName)
                .transactionType(testTransactionType)
                .common(testCommon)
                .build());

        Category testCategory = categoryRepository.findById(savedCategory.getCategoryId()).get();

        assertThat(testCategory.getCategoryName()).isEqualTo(testCategoryName);
        assertThat(testCategory.getTransactionType()).isEqualTo(testTransactionType);
        assertThat(testCategory.isCommon()).isEqualTo(testCommon);
    }
}
