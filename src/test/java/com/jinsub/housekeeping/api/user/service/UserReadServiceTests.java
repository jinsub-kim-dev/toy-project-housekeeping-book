package com.jinsub.housekeeping.api.user.service;

import com.jinsub.housekeeping.api.user.model.entity.User;
import com.jinsub.housekeeping.api.user.repository.UserRepository;
import com.jinsub.housekeeping.api.user.service.UserReadService;
import com.jinsub.housekeeping.base.exception.model.HouseKeepingException;
import com.jinsub.housekeeping.base.helper.CryptoHelper;
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
public class UserReadServiceTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserReadService userReadService;

    @Test
    public void Id로_유저를_조회한다() throws Exception {

        String testUserName = "test user name";
        String testUserEmail = "test@email.com";
        String testUserPassword = "test_user_password";

        User savedUser = userRepository.save(User.builder()
                .userName(testUserName)
                .hashedEmail(CryptoHelper.getSha256HashedString(testUserEmail))
                .hashedPassword(CryptoHelper.getSha256HashedString(testUserPassword))
                .build());

        User testUser = userReadService.getUserById(savedUser.getUserId());

        assertThat(testUser.getUserName()).isEqualTo(testUserName);
        assertThat(testUser.getHashedEmail()).isEqualTo(CryptoHelper.getSha256HashedString(testUserEmail));
        assertThat(testUser.getHashedPassword()).isEqualTo(CryptoHelper.getSha256HashedString(testUserPassword));
    }

    @Test
    public void 이름으로_유저를_조회한다() throws Exception {

        String testUserName = "test user name";
        String testUserEmail = "test@email.com";
        String testUserPassword = "test_user_password";

        User savedUser = userRepository.save(User.builder()
                .userName(testUserName)
                .hashedEmail(CryptoHelper.getSha256HashedString(testUserEmail))
                .hashedPassword(CryptoHelper.getSha256HashedString(testUserPassword))
                .build());

        User testUser = userReadService.getUserByName(savedUser.getUserName());

        assertThat(testUser.getUserName()).isEqualTo(testUserName);
        assertThat(testUser.getHashedEmail()).isEqualTo(CryptoHelper.getSha256HashedString(testUserEmail));
        assertThat(testUser.getHashedPassword()).isEqualTo(CryptoHelper.getSha256HashedString(testUserPassword));
    }

    @Test(expected = HouseKeepingException.class)
    public void 존재하지_않는_유저Id로_조회하는_경우() throws Exception {
        final long INVALID_USER_ID = -1;
        User testUser = userReadService.getUserById(INVALID_USER_ID);
    }

    @Test(expected = HouseKeepingException.class)
    public void 존재하지_않는_유저_이름으로_조회하는_경우() throws Exception {
        final String INVALID_USER_NAME = "";
        User testUser = userReadService.getUserByName(INVALID_USER_NAME);
    }
}
