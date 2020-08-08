package com.yuluo.auto;

import com.yuluo.auto.db.DBUtil;

/**
 * @Description 程序入口
 * @Author 蚂蚁不是ant
 * @Date 2020/8/5 21:29
 * @Version V1.0
 */
public class Application {
    public static void main(String[] args) {
        System.out.println("-----------> 程序启动 <-----------------");
        start();
        System.out.println("-----------> 执行完成 <-----------------");
    }

    /**
     * 执行初始化数据库操作
     */
    private static void start(){
        DBUtil.getInstance().init();
    }
}
