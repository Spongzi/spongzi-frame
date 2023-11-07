package com.spongzi.user.controller;

import com.spongzi.bean.Result;
import com.spongzi.entity.PageResult;
import com.spongzi.user.entity.dto.UserDto;
import com.spongzi.user.entity.po.UserPo;
import com.spongzi.user.entity.req.UserPageReq;
import com.spongzi.user.entity.req.UserReq;
import com.spongzi.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
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

    @DeleteMapping("/{id}")
    public Result<Integer> delete(@PathVariable Long id) {
        Integer result = userService.delete(id);
        return Result.ok(result);
    }

    @GetMapping("/all")
    public Result<PageResult<UserPo>> getUserPage(@RequestBody UserPageReq userPageReq) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userPageReq, userDto);
        PageResult<UserPo> userPage = userService.getUserPage(userDto);
        return Result.ok(userPage);
    }
}
