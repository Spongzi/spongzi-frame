package com.spongzi.user.service;


import com.spongzi.user.entity.dto.UserDto;
import com.spongzi.user.entity.po.UserPo;
import com.spongzi.user.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 实施用户服务
 *
 * @author spong
 * @date 2023/11/07
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int addUser(UserDto userDto) {
        UserPo userPo = new UserPo();
        BeanUtils.copyProperties(userDto, userPo);
        return userMapper.insert(userPo);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteById(id);
    }
}
