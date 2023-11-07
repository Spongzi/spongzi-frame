package com.spongzi.user.entity.req;

import lombok.Data;

/**
 * 用户请求
 *
 * @author spong
 * @date 2023/11/07
 */
@Data
public class UserPageReq {

    private Integer pageIndex;

    private Integer pageSize;
}
