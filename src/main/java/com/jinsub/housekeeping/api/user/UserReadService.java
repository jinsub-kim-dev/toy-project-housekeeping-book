package com.jinsub.housekeeping.api.user;

import com.jinsub.housekeeping.api.user.model.entity.User;
import com.jinsub.housekeeping.api.user.repository.UserRepository;
import com.jinsub.housekeeping.base.exception.enums.HouseKeepingErrorType;
import com.jinsub.housekeeping.base.exception.model.HouseKeepingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserReadService {

    // Repositories
    @Autowired
    UserRepository userRepository;

    public User getUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new HouseKeepingException(HouseKeepingErrorType.POLICY_VIOLATION_NOT_EXIST_USER, "user is not exist"));
    }

    public User getUserByName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(
                () -> new HouseKeepingException(HouseKeepingErrorType.POLICY_VIOLATION_NOT_EXIST_USER, "user is not exist"));
    }
}
