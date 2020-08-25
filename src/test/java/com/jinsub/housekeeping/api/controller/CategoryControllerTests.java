package com.jinsub.housekeeping.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinsub.housekeeping.api.category.enums.CategoryType;
import com.jinsub.housekeeping.api.category.model.dto.CategoryDto;
import com.jinsub.housekeeping.api.category.model.entity.Category;
import com.jinsub.housekeeping.api.category.repository.CategoryRepository;
import com.jinsub.housekeeping.api.transaction.enums.TransactionType;
import com.jinsub.housekeeping.base.model.CodeResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTests {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    CategoryRepository categoryRepository;

    @After
    public void tearDown() throws Exception {
        categoryRepository.deleteAll();
    }

    @Test
    public void 카테고리를_생성한다() {
        String testCategoryName = "test category name";
        TransactionType testTransactionType = TransactionType.EXPENSE;
        CategoryType testCategoryType = CategoryType.COMMON;
        CategoryDto.CreateRequest request = CategoryDto.CreateRequest.builder()
                .categoryName(testCategoryName)
                .transactionType(testTransactionType)
                .categoryType(testCategoryType)
                .build();

        String url = "http://localhost:" + port + "/api/v1/category";

        ResponseEntity<CodeResponse> responseEntity = testRestTemplate.postForEntity(url, request, CodeResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getCode()).isEqualTo(CodeResponse.SUCCESS_CODE);

        ObjectMapper objectMapper = new ObjectMapper();
        CodeResponse codeResponse = CodeResponse.class.cast(responseEntity.getBody());
        CategoryDto.CreateResponse response = objectMapper.convertValue(codeResponse.getResult(), CategoryDto.CreateResponse.class);
        Category testCategory = categoryRepository.findById(response.getCategoryId()).get();

        assertThat(testCategory.getCategoryName()).isEqualTo(testCategoryName);
        assertThat(testCategory.getTransactionType()).isEqualTo(testTransactionType);
        assertThat(testCategory.getCategoryType()).isEqualTo(testCategoryType);
    }

    @Test
    public void 아이디로_카테고리를_조회한다() {
        String testCategoryName = "test category name";
        TransactionType testTransactionType = TransactionType.EXPENSE;
        CategoryType testCategoryType = CategoryType.COMMON;
        Category savedCategory = categoryRepository.save(Category.builder()
                .categoryName(testCategoryName)
                .transactionType(testTransactionType)
                .categoryType(testCategoryType)
                .build());

        String url = "http://localhost:" + port + "/api/v1/category/id?categoryId=" + savedCategory.getCategoryId();

        ResponseEntity<CodeResponse> responseEntity = testRestTemplate.getForEntity(url, CodeResponse.class);

        ObjectMapper objectMapper = new ObjectMapper();
        CodeResponse codeResponse = CodeResponse.class.cast(responseEntity.getBody());
        CategoryDto.ReadResponse response = objectMapper.convertValue(codeResponse.getResult(), CategoryDto.ReadResponse.class);

        assertThat(response.getCategoryName()).isEqualTo(testCategoryName);
        assertThat(response.getTransactionType()).isEqualTo(testTransactionType);
        assertThat(response.getCategoryType()).isEqualTo(testCategoryType);
    }
}
