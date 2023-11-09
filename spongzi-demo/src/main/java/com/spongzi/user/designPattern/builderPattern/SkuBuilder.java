package com.spongzi.user.designPattern.builderPattern;

/**
 * sku builder
 *
 * @author spong
 * @date 2023/11/09
 */
public class SkuBuilder<T extends SkuVO> {

    private SkuDO skuDO;

    private Boolean needCoupon;

    private SkuVOFunction<T> skuVOFunction;

    private SkuVOExtFunction<T> skuVOExtFunction;

    public static <T extends SkuVO> SkuBuilder<T> create() {
        return new SkuBuilder<>();
    }

    public SkuBuilder<T> skuDO(SkuDO skuDO) {
        this.skuDO = skuDO;
        return this;
    }

    public SkuBuilder<T> needCoupon(Boolean needCoupon) {
        this.needCoupon = needCoupon;
        return this;
    }

    public SkuBuilder<T> skuVOFunction(SkuVOFunction<T> skuVOFunction) {
        this.skuVOFunction = skuVOFunction;
        return this;
    }

    public SkuBuilder<T> skuDO(SkuVOExtFunction<T> skuVOExtFunction) {
        this.skuVOExtFunction = skuVOExtFunction;
        return this;
    }

    public T build() {
        T skuVO = skuVOFunction.newInstance();
        skuVO.setSkuId(skuDO.getSkuId());
        skuVO.setSkuName(skuDO.getSkuName());
        if (needCoupon) {
            // 转化逻辑
            skuVO.setCouponText("");
        }
        if (this.skuVOExtFunction != null) {
            this.skuVOExtFunction.buildExtInfo(skuVO, skuDO);
        }
        return skuVO;
    }
}
