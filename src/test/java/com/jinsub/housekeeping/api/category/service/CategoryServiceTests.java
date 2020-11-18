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
public class CategoryServiceTests {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryService categoryService;

    @Test
    public void 카테고리를_생성한다() {
        String testCategoryName = "test category name";
        TransactionType testTransactionType = TransactionType.EXPENSE;
        CategoryType testCategoryType = CategoryType.COMMON;

        Category savedCategory = categoryService.createCategory(testCategoryName, testTransactionType, testCategoryType);

        Category testCategory = categoryRepository.findById(savedCategory.getCategoryId()).get();

        assertThat(testCategory.getCategoryName()).isEqualTo(testCategoryName);
        assertThat(testCategory.getTransactionType()).isEqualTo(testTransactionType);
        assertThat(testCategory.getCategoryType()).isEqualTo(testCategoryType);
    }

    @Test(expected = HouseKeepingException.class)
    public void 중복된_이름으로_카테고리를_생성하는_경우() {
        String testCategoryName = "test category name";
        TransactionType testTransactionType = TransactionType.EXPENSE;
        CategoryType testCategoryType = CategoryType.COMMON;

        // 정상 실행
        categoryService.createCategory(testCategoryName, testTransactionType, testCategoryType);

        // 비정상 실행
        categoryService.createCategory(testCategoryName, testTransactionType, testCategoryType);
    }
}
