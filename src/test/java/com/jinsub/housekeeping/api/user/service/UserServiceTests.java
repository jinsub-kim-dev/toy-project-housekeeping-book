package com.jinsub.housekeeping.api.user.service;

import com.jinsub.housekeeping.api.user.model.entity.User;
import com.jinsub.housekeeping.api.user.service.UserService;
import com.jinsub.housekeeping.base.exception.model.HouseKeepingException;
import com.jinsub.housekeeping.base.helper.CryptoHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    public void 유저를_생성한다() throws NoSuchAlgorithmException {
        String testUserName = "test user name";
        String testUserEmail = "test@email.com";
        String testUserPassword = "test_user_password";

        User createdUser = userService.createUser(testUserName, testUserEmail, testUserPassword);

        assertThat(createdUser.getUserName()).isEqualTo(testUserName);
        assertThat(createdUser.getHashedEmail()).isEqualTo(CryptoHelper.getSha256HashedString(testUserEmail));
        assertThat(createdUser.getHashedPassword()).isEqualTo(CryptoHelper.getSha256HashedString(testUserPassword));
    }

    @Test(expected = HouseKeepingException.class)
    public void 중복된_이름으로_유저를_생성하는_경우() throws NoSuchAlgorithmException {
        String testUserName = "test user name";
        String testUserEmail = "test@email.com";
        String testUserPassword = "test_user_password";

        // 정상적으로 실행된 생성 로직
        userService.createUser(testUserName, testUserEmail, testUserPassword);

        // 이름이 중복되는 비정상 생성 로직, HouseKeepingException 예외 발생
        userService.createUser(testUserName, testUserEmail, testUserPassword);
    }
}
