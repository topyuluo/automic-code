package com.yuluo.auto.util;

/**
 * @Description
 * @Author 蚂蚁不是ant
 * @Date 2020/8/8 0:08
 * @Version V1.0
 */
public class StringUtils {

    /**
     * 字符串首字母大写 驼峰格式
     * @return
     */
    public static  String getFirstUpperCaseName(String tableName) {
        String[] names = tableName.split("_");
        StringBuilder sb = new StringBuilder();
        for (String name : names) {
            sb.append(name.substring(0, 1).toUpperCase()).append(name.substring(1));
        }
        return sb.toString();
    }

    /**
     * 首字母小写
     * @return
     */
    public static String getFirstLowerCaseName(String upperCaseName) {
        char[] chars = upperCaseName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
