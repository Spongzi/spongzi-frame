package com.spongzi.bean;

import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

/**
 * 页面结果
 *
 * @author spong
 * @date 2023/11/07
 */
@Setter
public class PageResult implements Serializable {

    private static final long serialVersionUID = -1155216437497577090L;

    private Long pageNo = 1L;

    private Long pageSize = 10L;

    public Long getPageNo() {
        if (pageNo == null || pageNo < 1) {
            return 1L;
        }
        return pageNo;
    }

    public Long getPageSize() {
        if (pageSize == null || pageSize < 1) {
            return 10L;
        }
        return pageSize;
    }
}
