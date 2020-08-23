package com.jinsub.housekeeping.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinsub.housekeeping.api.user.model.dto.UserDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTests {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    UserRepository userRepository;

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void 유저를_등록한다() throws NoSuchAlgorithmException {

        String testUserName = "test user name";
        String testUserEmail = "test@email.com";
        String testUserPassword = "test_user_password";
        UserDto.CreateRequest request = UserDto.CreateRequest.builder()
                .userName(testUserName)
                .email(testUserEmail)
                .password(testUserPassword)
                .build();

        String url = "http://localhost:" + port + "/api/v1/user";

        ResponseEntity<CodeResponse> responseEntity = testRestTemplate.postForEntity(url, request, CodeResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getCode()).isEqualTo(CodeResponse.SUCCESS_CODE);

        ObjectMapper objectMapper = new ObjectMapper();
        CodeResponse codeResponse = CodeResponse.class.cast(responseEntity.getBody());
        UserDto.CreateResponse response = objectMapper.convertValue(codeResponse.getResult(), UserDto.CreateResponse.class);
        User testUser = userRepository.findById(response.getUserId()).get();

        assertThat(testUser.getUserName()).isEqualTo(testUserName);
    }

    @Test
    public void 아이디로_유저를_조회한다() throws NoSuchAlgorithmException {

        String testUserName = "test user name";
        String testUserEmail = "test@email.com";
        String testUserPassword = "test_user_password";
        User savedUser = userRepository.save(User.builder()
                .userName(testUserName)
                .hashedEmail(testUserEmail)
                .hashedPassword(testUserPassword)
                .build());

        String url = "http://localhost:" + port + "/api/v1/user/id?userId=" + savedUser.getUserId();

        ResponseEntity<CodeResponse> responseEntity = testRestTemplate.getForEntity(url, CodeResponse.class);

        ObjectMapper objectMapper = new ObjectMapper();
        CodeResponse codeResponse = CodeResponse.class.cast(responseEntity.getBody());
        UserDto.ReadResponse response = objectMapper.convertValue(codeResponse.getResult(), UserDto.ReadResponse.class);
        User testUser = userRepository.findById(response.getUserId()).get();

        assertThat(testUser.getUserName()).isEqualTo(testUserName);
    }

    @Test
    public void 이름으로_유저를_조회한다() throws NoSuchAlgorithmException {

        String testUserName = "test user name";
        String testUserEmail = "test@email.com";
        String testUserPassword = "test_user_password";
        User savedUser = userRepository.save(User.builder()
                .userName(testUserName)
                .hashedEmail(testUserEmail)
                .hashedPassword(testUserPassword)
                .build());

        String url = "http://localhost:" + port + "/api/v1/user/name?userName=" + savedUser.getUserName();

        ResponseEntity<CodeResponse> responseEntity = testRestTemplate.getForEntity(url, CodeResponse.class);

        ObjectMapper objectMapper = new ObjectMapper();
        CodeResponse codeResponse = CodeResponse.class.cast(responseEntity.getBody());
        UserDto.ReadResponse response = objectMapper.convertValue(codeResponse.getResult(), UserDto.ReadResponse.class);
        User testUser = userRepository.findById(response.getUserId()).get();

        assertThat(testUser.getUserName()).isEqualTo(testUserName);
    }
}
