package com.spongzi.user.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.spongzi.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户po
 *
 * @author spong
 * @date 2023/11/07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user")
public class UserPo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -9088784851664786546L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

    private Integer age;

}
