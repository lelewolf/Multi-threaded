package com.helele;

/**
 * @version 1.0.0
 * @description: 线程调用的随机性
 * @create: 2021-06-13 23:06
 * @author: lang
 */
public class MultiThreadedTest02 {
    public static void main(String[] args) {
        Thread thread = new Thread(()-> System.out.println("MyThread is run "+ Thread.currentThread().getName()));
        thread.start();
        // thread.start(); java.lang.IllegalThreadStateException
        System.out.println("程序运行结束");
    }
}
