package com.yuluo.auto.util;

/**
 * @Description
 * @Author 蚂蚁不是ant
 * @Date 2020/8/7 22:16
 * @Version V1.0
 */
public abstract class Assert {

    public static void notEmpty(String content, String msg) {
        if ("".equals(msg)){
            throw new IllegalArgumentException(msg);
        }
    }


    public static void notNull(Object obj, String msg) {
        if (obj == null){
            throw new IllegalArgumentException(msg);
        }
    }
}
