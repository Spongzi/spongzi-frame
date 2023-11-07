package com.spongzi.user.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户po
 *
 * @author spong
 * @date 2023/11/07
 */
@TableName("user")
@Data
public class UserDto {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;
}
