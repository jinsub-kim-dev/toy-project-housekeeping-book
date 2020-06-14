package com.jinsub.housekeeping.api.user.repository;


import com.jinsub.housekeeping.api.user.model.entity.User;
import com.jinsub.housekeeping.base.helper.CryptoHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void 유저를_생성한다() throws Exception {

        String testUserName = "test user name";
        String testUserEmail = "test@email.com";
        String testUserPassword = "test_user_password";

        User savedUser = userRepository.save(User.builder()
                .userName(testUserName)
                .hashedEmail(CryptoHelper.getSha256HashedString(testUserEmail))
                .hashedPassword(CryptoHelper.getSha256HashedString(testUserPassword))
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build());

        User testUser = userRepository.findById(savedUser.getUserId()).get();

        assertThat(testUser.getUserName()).isEqualTo(testUserName);
        assertThat(testUser.getHashedEmail()).isEqualTo(CryptoHelper.getSha256HashedString(testUserEmail));
        assertThat(testUser.getHashedPassword()).isEqualTo(CryptoHelper.getSha256HashedString(testUserPassword));
    }
}
