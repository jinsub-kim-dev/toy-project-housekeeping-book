package com.jinsub.housekeeping.api.controller;

import com.jinsub.housekeeping.api.user.model.dto.UserDto;
import com.jinsub.housekeeping.api.user.model.entity.User;
import com.jinsub.housekeeping.api.user.service.UserService;
import com.jinsub.housekeeping.base.model.CodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping({"", "/"})
    @ResponseBody
    CodeResponse createUser(@RequestBody UserDto.CreateRequest request) throws NoSuchAlgorithmException {
        User createdUser = userService.createUser(request.getUserName(), request.getEmail(), request.getPassword());
        UserDto.CreateResponse createdUserResponse = UserDto.CreateResponse.of(createdUser);
        return CodeResponse.successResult(createdUserResponse);
    }
}
