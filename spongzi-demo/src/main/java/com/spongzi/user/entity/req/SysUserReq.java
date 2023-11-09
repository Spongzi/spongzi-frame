package com.spongzi.user.entity.req;

import com.spongzi.bean.PageResponse;
import com.spongzi.bean.PageResult;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysUser)实体类
 *
 * @author spongzi
 * @since 2023-11-07 15:56:35
 */
@Data
public class SysUserReq extends PageResult implements Serializable {
    private static final long serialVersionUID = 808861062624901833L;

    private Long id;

    private String name;

    private Integer age;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String version;

    private Integer deleteFlag;

}

