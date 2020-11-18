package com.jinsub.housekeeping.api.category.service;

import com.jinsub.housekeeping.api.category.enums.CategoryType;
import com.jinsub.housekeeping.api.category.model.entity.Category;
import com.jinsub.housekeeping.api.category.repository.CategoryRepository;
import com.jinsub.housekeeping.api.transaction.enums.TransactionType;
import com.jinsub.housekeeping.base.exception.model.HouseKeepingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@DataJpaTest
public class CategoryReadServiceTests {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryReadService categoryReadService;

    @Test
    public void Id로_카테고리를_조회한다() {

        String testCategoryName = "test category name";
        TransactionType testTransactionType = TransactionType.EXPENSE;
        CategoryType testCategoryType = CategoryType.COMMON;

        Category savedCategory = categoryRepository.save(Category.builder()
                .categoryName(testCategoryName)
                .transactionType(testTransactionType)
                .categoryType(testCategoryType)
                .build());

        Category testCategory = categoryReadService.getCategoryById(savedCategory.getCategoryId());

        assertThat(testCategory.getCategoryName()).isEqualTo(testCategoryName);
        assertThat(testCategory.getTransactionType()).isEqualTo(testTransactionType);
        assertThat(testCategory.getCategoryType()).isEqualTo(testCategoryType);
    }

    @Test(expected = HouseKeepingException.class)
    public void 존재하지_않는_카테고리Id로_조회하는_경우() {
        final long INVALID_CATEGORY_ID = -1;
        Category testCategory = categoryReadService.getCategoryById(INVALID_CATEGORY_ID);
    }
}
