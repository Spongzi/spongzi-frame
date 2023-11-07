package com.spongzi.user.controller;

import com.spongzi.Result;
import com.spongzi.user.entity.dto.UserDto;
import com.spongzi.user.entity.req.UserReq;
import com.spongzi.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("user")
    public Result<Integer> addUser(@RequestBody UserReq userReq) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userReq, userDto);
        int count = userService.addUser(userDto);
        return Result.ok(count);
    }
}
