package com.spongzi.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spongzi.user.entity.po.UserPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserPo> {

    IPage<UserPo> getUserPage(IPage<UserPo> userPoIPage);

}
