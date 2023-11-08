package com.spongzi.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
public class PageResponse<T> implements Serializable {

    private static final long serialVersionUID = 1692866368885939554L;

    private Long pageNo = 1L;

    private Long pageSize = 10L;

    private Long total = 0L;

    private Long totalPage = 0L;

    private List<T> result = Collections.emptyList();

    private Long start = 1L;

    private Long end = 0L;

    public PageResponse() {
    }

    public void setRecords(List<T> result) {
        this.result = result;
        if (result != null && !result.isEmpty() && this.total == 0) {
            this.setTotal((long) result.size());
        }
    }

    public void setCurrent(Long pageNo) {
        if (pageNo != null && pageNo > 0) {
            this.pageNo = pageNo;
        }
    }

    public void setPageSize(Long pageSize) {
        if (pageSize != null && pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public void setTotal(Long total) {
        this.total = total;
        if (total == -1) {
            this.pageNo = 1L;
            return;
        }
        if (pageSize > 0) {
            this.totalPage = total / this.pageSize + (total % this.pageSize > 0 ? 1 : 0);
        } else {
            this.totalPage = 0L;
        }
        this.start = (this.pageNo > 0 ? (this.pageNo * this.pageSize) : 1);
        this.end = this.start - 1 + this.pageSize * (this.pageNo > 0 ? this.pageNo : 1);
    }
}
