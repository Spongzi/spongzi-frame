package com.spongzi.user.designPattern.builderPattern;

/**
 * SKU Vo ext函数
 *
 * @author spong
 * @date 2023/11/09
 */
public interface SkuVOExtFunction<T extends SkuVO> {

    void buildExtInfo(T skuVO, SkuDO skuDO);

}
