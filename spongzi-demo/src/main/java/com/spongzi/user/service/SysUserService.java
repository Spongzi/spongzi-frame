package com.spongzi.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spongzi.bean.PageResponse;
import com.spongzi.user.entity.po.SysUser;
import com.spongzi.user.entity.req.SysUserReq;

/**
 * (SysUser)表服务接口
 *
 * @author spongzi
 * @since 2023-11-07 15:56:35
 */
public interface SysUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUser queryById(Long id);

    /**
     * 按页查询
     * 分页查询
     *
     * @param sysUserReq 系统用户请求
     * @return 查询结果
     */
    PageResponse<SysUser> queryByPage(SysUserReq sysUserReq);

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser insert(SysUser sysUser);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
