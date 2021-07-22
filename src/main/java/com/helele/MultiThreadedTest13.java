package com.helele;

/**
 * @version 1.0.0
 * @description: Thread.currentThread表示当前代码段正在被哪个线程调用的相关信息，this表示的是当前对象，与Thread.currentThread有很大的区别。
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest13 {
    public static void main(String[] args) {
        final Thread thread = Thread.currentThread();
        System.out.println(thread.getName() +" "+ thread.getId());//main 1
    }

}
