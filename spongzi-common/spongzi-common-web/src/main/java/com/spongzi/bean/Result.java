package com.spongzi.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 结果
 *
 * @author spong
 * @date 2023/11/07
 */
@Data
public class Result<T> implements Serializable {

    private Result() {}

    private static final long serialVersionUID = -4699921417763260909L;

    private Boolean success;

    private Integer code;

    private String message;

    private T data;

    /**
     * 成功--不带参数
     *
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> ok() {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage(ResultMessage.SUCCESS);
        return result;
    }

    /**
     * 成功--自定义编码
     *
     * @param resultCode    结果代码
     * @param resultMessage 结果消息
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> ok(Integer resultCode, String resultMessage) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(resultCode);
        result.setMessage(resultMessage);
        return result;
    }

    /**
     * 成功--带data参数
     *
     * @param data 数据
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage(ResultMessage.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 成功--带data参数且自定义code和message
     *
     * @param data          数据
     * @param resultCode    结果代码
     * @param resultMessage 结果消息
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> ok(Integer resultCode, String resultMessage, T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(resultCode);
        result.setMessage(resultMessage);
        result.setData(data);
        return result;
    }

    /**
     * 失败--不带参数
     *
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> fail() {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMessage(ResultMessage.ERROR);
        return result;
    }

    /**
     * 失败--自定义编码
     *
     * @param resultCode    结果代码
     * @param resultMessage 结果消息
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> fail(Integer resultCode, String resultMessage) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(resultCode);
        result.setMessage(resultMessage);
        return result;
    }

    /**
     * 失败--带data参数
     *
     * @param data 数据
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> fail(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMessage(ResultMessage.ERROR);
        result.setData(data);
        return result;
    }

    /**
     * 失败--带data参数且自定义code和message
     *
     * @param data          数据
     * @param resultCode    结果代码
     * @param resultMessage 结果消息
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> fail(Integer resultCode, String resultMessage, T data) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(resultCode);
        result.setMessage(resultMessage);
        result.setData(data);
        return result;
    }
}
