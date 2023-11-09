package com.spongzi.user.convert;

import com.spongzi.user.entity.po.SysUser;
import com.spongzi.user.entity.req.SysUserReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysUserConvert {

    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    SysUser convertReqToPo(SysUserReq sysUserReq);

}
