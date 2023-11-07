package com.spongzi.user.service;

import com.spongzi.entity.PageResult;
import com.spongzi.user.entity.dto.UserDto;
import com.spongzi.user.entity.po.UserPo;

/**
 * 用户服务
 *
 * @author spong
 * @date 2023/11/07
 */
public interface UserService {

    /**
     * 添加用户
     *
     * @param userDto 用户数据收件人
     * @return int
     */
    int addUser(UserDto userDto);

    /**
     * 删除
     *
     * @param id ID
     * @return {@link Boolean}
     */
    int delete(Long id);

    /**
     * 获取用户页面
     *
     * @param userDto 用户数据收件人
     * @return {@link PageResult}<{@link UserPo}>
     */
    PageResult<UserPo> getUserPage(UserDto userDto);
}
