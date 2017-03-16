package com.sevenstar.task;

import java.lang.reflect.Method;

/**
 * Created by JACKSON on 2017/3/16.
 */
public class 执行任务 {


    public void test(String vv){
        System.out.println(vv);
    }

    public static void main(String[] args) {
        执行任务 exe = new 执行任务();
        String what = "that";
        exe.test(what);

        Method[] declaredMethods = 执行任务.class.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());

        }
    }

}
