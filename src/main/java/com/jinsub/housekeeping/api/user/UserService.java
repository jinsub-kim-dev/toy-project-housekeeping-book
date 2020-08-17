package com.jinsub.housekeeping.api.user;

import com.jinsub.housekeeping.api.user.model.entity.User;
import com.jinsub.housekeeping.api.user.repository.UserRepository;
import com.jinsub.housekeeping.base.exception.enums.HouseKeepingErrorType;
import com.jinsub.housekeeping.base.exception.model.HouseKeepingException;
import com.jinsub.housekeeping.base.helper.CryptoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    // Repositories
    @Autowired
    UserRepository userRepository;

    public User createUser(String userName, String email, String password) throws NoSuchAlgorithmException {
        if (userRepository.existsByUserName(userName)) {
            throw new HouseKeepingException(HouseKeepingErrorType.POLICY_VIOLATION_ALREADY_EXIST_USER, "user is already exist");
        }

        return userRepository.save(User.builder()
                .userName(userName)
                .hashedEmail(CryptoHelper.getSha256HashedString(email))
                .hashedPassword(CryptoHelper.getSha256HashedString(password))
                .build());
    }
}
