package com.spongzi.user.designPattern.builderPattern;

import lombok.Data;

@Data
public class SkuDO {

    /**
     * SKU ID
     */
    private Long skuId;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * 促销ID
     */
    private Long promotionId;

    /**
     * 优惠券ID
     */
    private Long couponId;

}
