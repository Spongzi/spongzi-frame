package com.spongzi.tool.util;

/**
 * 图像实用程序
 *
 * @author spong
 * @date 2023/11/08
 */
public class ImageUtils {

    /**
     * 获取图片域名
     */
    public static String getImageDomain(Long id) {

        String imageDomain = "//jichi/img1.com/pic/";
        try {
            int index = (int) (id % 5);
            switch (index) {
                case 0:
                    imageDomain = "//jichi/img1.com/pic/";
                    break;
                case 1:
                    imageDomain = "//jichi/img2.com/pic/";
                    break;
                case 2:
                    imageDomain = "//jichi/img3.com/pic/";
                    break;
                case 3:
                    imageDomain = "//jichi/img4.com/pic/";
                    break;
                case 4:
                    imageDomain = "//jingdianjichi/img1.com/pic/";
                    break;
                default:
                    imageDomain = "//jingdianjichi/img3.com/pic/";
                    break;
            }
            imageDomain = "https:" + imageDomain;
        } catch (Exception e) {
            return imageDomain;
        }
        return imageDomain;
    }


}
