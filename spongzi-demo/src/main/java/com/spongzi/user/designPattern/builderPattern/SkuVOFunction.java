package com.spongzi.user.designPattern.builderPattern;

/**
 * SKU投票功能
 *
 * @author spong
 * @date 2023/11/09
 */
public interface SkuVOFunction<T extends SkuVO> {

    T newInstance();

}
