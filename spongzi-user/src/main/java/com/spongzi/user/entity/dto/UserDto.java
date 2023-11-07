package com.spongzi.user.entity.dto;

import lombok.Data;

/**
 * 用户po
 *
 * @author spong
 * @date 2023/11/07
 */
@Data
public class UserDto {

    private String name;

    private Integer age;

    private Integer pageIndex;

    private Integer pageSize;
}
