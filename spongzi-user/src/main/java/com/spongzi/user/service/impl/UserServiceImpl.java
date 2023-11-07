package com.spongzi.user.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spongzi.entity.PageResult;
import com.spongzi.user.entity.dto.UserDto;
import com.spongzi.user.entity.po.UserPo;
import com.spongzi.user.mapper.UserMapper;
import com.spongzi.user.service.UserService;
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

    @Override
    public PageResult<UserPo> getUserPage(UserDto userDto) {
        IPage<UserPo> userPoPage = new Page<>(userDto.getPageIndex(), userDto.getPageSize());
        IPage<UserPo> userPage = userMapper.getUserPage(userPoPage);
        PageResult<UserPo> userPoPageResult = new PageResult<>();
        userPoPageResult.loadData(userPage);
        return userPoPageResult;
    }
}
