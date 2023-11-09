package com.spongzi.user.designPattern.builderPattern;

import lombok.Data;

@Data
public class SkuVO {

    /**
     * SKU ID
     */
    private Long skuId;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * 促销文本
     */
    private String promotionText;

    /**
     * 优惠券文本
     */
    private String couponText;

}
