package com.jinsub.housekeeping.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinsub.housekeeping.api.category.enums.CategoryType;
import com.jinsub.housekeeping.api.category.model.entity.Category;
import com.jinsub.housekeeping.api.category.repository.CategoryRepository;
import com.jinsub.housekeeping.api.transaction.enums.AssetType;
import com.jinsub.housekeeping.api.transaction.enums.TransactionType;
import com.jinsub.housekeeping.api.transaction.model.dto.*;
import com.jinsub.housekeeping.api.transaction.model.entity.Transaction;
import com.jinsub.housekeeping.api.transaction.repository.TransactionRepository;
import com.jinsub.housekeeping.api.transaction.service.TransactionService;
import com.jinsub.housekeeping.api.user.model.entity.User;
import com.jinsub.housekeeping.api.user.repository.UserRepository;
import com.jinsub.housekeeping.base.model.CodeResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerTests {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TransactionService transactionService;

    @After
    public void tearDown() throws Exception {
        transactionRepository.deleteAll();
    }

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

        long testAmountOfMoney = 1000L;
        String testDetails = "test details";
        CreateTransactionRequestDto request = CreateTransactionRequestDto.builder()
                .userId(user.getUserId())
                .transactionType(TransactionType.EXPENSE)
                .transactionDate(LocalDateTime.now())
                .assetType(AssetType.CARD)
                .categoryId(category.getCategoryId())
                .amountOfMoney(testAmountOfMoney)
                .details(testDetails)
                .build();

        String url = "http://localhost:" + port + "/api/v1/transaction";

        ResponseEntity<CodeResponse> responseEntity = testRestTemplate.postForEntity(url, request, CodeResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getCode()).isEqualTo(CodeResponse.SUCCESS_CODE);

        ObjectMapper objectMapper = new ObjectMapper();
        CodeResponse codeResponse = CodeResponse.class.cast(responseEntity.getBody());
        CreateTransactionResponseDto response = objectMapper.convertValue(codeResponse.getResult(), CreateTransactionResponseDto.class);
        Transaction testTransaction = transactionRepository.findById(response.getTransactionDto().getTransactionId()).get();

        assertThat(testTransaction.getTransactionType()).isEqualTo(TransactionType.EXPENSE);
        assertThat(testTransaction.getAmountOfMoney()).isEqualTo(testAmountOfMoney);
        assertThat(testTransaction.getDetails()).isEqualTo(testDetails);
    }

    @Test
    public void 아이디로_트랜잭션을_조회한다() {
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

        long testAmountOfMoney = 1000L;
        String testDetails = "test details";
        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.EXPENSE)
                .transactionDate(LocalDateTime.now())
                .assetType(AssetType.CASH)
                .amountOfMoney(testAmountOfMoney)
                .details(testDetails)
                .build();
        transaction.setUser(user);
        transaction.setCategory(category);

        Transaction savedTransaction = transactionRepository.save(transaction);

        String url = "http://localhost:" + port + "/api/v1/transaction/id?transactionId=" + savedTransaction.getTransactionId();

        ResponseEntity<CodeResponse> responseEntity = testRestTemplate.getForEntity(url, CodeResponse.class);

        ObjectMapper objectMapper = new ObjectMapper();
        CodeResponse codeResponse = CodeResponse.class.cast(responseEntity.getBody());
        ReadTransactionResponseDto response = objectMapper.convertValue(codeResponse.getResult(), ReadTransactionResponseDto.class);
        TransactionDto testTransactionDto = response.getTransactionDto();

        assertThat(testTransactionDto.getAmountOfMoney()).isEqualTo(savedTransaction.getAmountOfMoney());
        assertThat(testTransactionDto.getDetails()).isEqualTo(savedTransaction.getDetails());
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
        LocalDateTime modifiedTransactionDate = LocalDateTime.parse("2020-11-20T01:59:49", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        AssetType modifiedAssetType = AssetType.CARD;
        long modifiedAmountOfMoney = 2000L;
        String modifiedDetails = "modified details";
        UpdateTransactionRequestDto request = UpdateTransactionRequestDto.builder()
                .transactionId(savedTransaction.getTransactionId())
                .transactionType(modifiedTransactionType)
                .transactionDate(modifiedTransactionDate)
                .assetType(modifiedAssetType)
                .amountOfMoney(modifiedAmountOfMoney)
                .details(modifiedDetails)
                .build();

        String url = "http://localhost:" + port + "/api/v1/transaction";

        HttpEntity<UpdateTransactionRequestDto> requestEntity  = new HttpEntity<>(request);

        ResponseEntity<CodeResponse> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT, requestEntity, CodeResponse.class);
        ObjectMapper objectMapper = new ObjectMapper();
        CodeResponse codeResponse = CodeResponse.class.cast(responseEntity.getBody());
        UpdateTransactionResponseDto response = objectMapper.convertValue(codeResponse.getResult(), UpdateTransactionResponseDto.class);
        TransactionDto testTransactionDto = response.getTransactionDto();

        assertThat(testTransactionDto.getTransactionType()).isEqualTo(modifiedTransactionType);
        assertThat(testTransactionDto.getTransactionDate()).isEqualTo(modifiedTransactionDate);
        assertThat(testTransactionDto.getAssetType()).isEqualTo(modifiedAssetType);
        assertThat(testTransactionDto.getAmountOfMoney()).isEqualTo(modifiedAmountOfMoney);
        assertThat(testTransactionDto.getDetails()).isEqualTo(modifiedDetails);
    }
}
