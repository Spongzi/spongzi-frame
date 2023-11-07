package com.spongzi.user.controller;

import com.spongzi.user.entity.dto.UserDto;
import com.spongzi.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("user")
    public int addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }
}
