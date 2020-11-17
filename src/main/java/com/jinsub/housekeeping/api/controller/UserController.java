package com.jinsub.housekeeping.api.controller;

import com.jinsub.housekeeping.api.user.model.dto.CreateUserRequestDto;
import com.jinsub.housekeeping.api.user.model.dto.CreateUserResponseDto;
import com.jinsub.housekeeping.api.user.model.dto.ReadUserResponseDto;
import com.jinsub.housekeeping.api.user.model.entity.User;
import com.jinsub.housekeeping.api.user.service.UserReadService;
import com.jinsub.housekeeping.api.user.service.UserService;
import com.jinsub.housekeeping.base.model.CodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserReadService userReadService;

    @PostMapping({"", "/"})
    @ResponseBody
    CodeResponse createUser(@RequestBody CreateUserRequestDto request) throws NoSuchAlgorithmException {
        User createdUser = userService.createUser(request.getUserName(), request.getEmail(), request.getPassword());
        CreateUserResponseDto createdUserResponse = CreateUserResponseDto.of(createdUser);
        return CodeResponse.successResult(createdUserResponse);
    }

    @GetMapping("/id")
    @ResponseBody
    CodeResponse readUserById(@RequestParam long userId) {
        User readUser = userReadService.getUserById(userId);
        ReadUserResponseDto readUserResponse = ReadUserResponseDto.of(readUser);
        return CodeResponse.successResult(readUserResponse);
    }

    @GetMapping("/name")
    @ResponseBody
    CodeResponse readUserByName(@RequestParam String userName) {
        User readUser = userReadService.getUserByName(userName);
        ReadUserResponseDto readUserResponse = ReadUserResponseDto.of(readUser);
        return CodeResponse.successResult(readUserResponse);
    }
}
