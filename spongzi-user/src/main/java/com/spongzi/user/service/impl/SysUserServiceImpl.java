package com.spongzi.user.service.impl;

import com.spongzi.bean.PageResponse;
import com.spongzi.user.convert.SysUserConvert;
import com.spongzi.user.mapper.SysUserDao;
import com.spongzi.user.entity.po.SysUser;
import com.spongzi.user.entity.req.SysUserReq;
import com.spongzi.user.service.SysUserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysUser)表服务实现类
 *
 * @author spongzi
 * @since 2023-11-07 15:56:35
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    @Cacheable(cacheNames = "sysUser", key = "'querySysUserById' + #id")
    public SysUser queryById(Long id) {
        return this.sysUserDao.queryById(id);
    }

    /**
     * 按页查询
     *
     * @param sysUserReq 系统用户请求
     * @return {@link PageResponse}<{@link SysUser}>
     */
    @Override
    public PageResponse<SysUser> queryByPage(SysUserReq sysUserReq) {
        SysUser sysUser = SysUserConvert.INSTANCE.convertReqToPo(sysUserReq);
        PageResponse<SysUser> pageResponse = new PageResponse<>();
        pageResponse.setCurrent(sysUserReq.getPageNo());
        pageResponse.setPageSize(sysUserReq.getPageSize());
        Long pageStart = (sysUserReq.getPageNo() - 1) * sysUserReq.getPageSize();
        Long total = this.sysUserDao.count(sysUser);
        List<SysUser> sysUserList = this.sysUserDao.queryAllByLimit(sysUser, pageStart, sysUserReq.getPageSize());
        pageResponse.setTotal(total);
        pageResponse.setRecords(sysUserList);
        return pageResponse;
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser insert(SysUser sysUser) {
        this.sysUserDao.insert(sysUser);
        return sysUser;
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser update(SysUser sysUser) {
        this.sysUserDao.update(sysUser);
        return this.queryById(sysUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysUserDao.deleteById(id) > 0;
    }
}
