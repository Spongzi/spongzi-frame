package com.spongzi.bean;

/**
 * 结果代码
 *
 * @author spong
 * @date 2023/11/07
 */
public interface ResultCode {

    /**
     * 成功
     */
    Integer SUCCESS = 200;

    /**
     * 错误
     */
    Integer ERROR = 500;

    /**
     * 参数不存在
     */
    Integer PARAM_NOT_EXIST = 499;
}
