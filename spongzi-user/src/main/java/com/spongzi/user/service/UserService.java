package com.spongzi.user.service;

import com.spongzi.user.entity.dto.UserDto;

/**
 * 用户服务
 *
 * @author spong
 * @date 2023/11/07
 */
public interface UserService {

    int addUser(UserDto userDto);
}
