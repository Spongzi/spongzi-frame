package com.spongzi.tool.util;

import java.text.SimpleDateFormat;

/**
 * 简单日期格式实用程序
 *
 * @author spong
 * @date 2023/11/08
 */
public class SimpleDateFormatUtils {

    private static final ThreadLocal<SimpleDateFormat> THREAD_LOCAL = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static SimpleDateFormat getTime() {
        SimpleDateFormat simpleDateFormat = THREAD_LOCAL.get();
        if(simpleDateFormat == null){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return simpleDateFormat;
    }

}