package com.helele;

/**
 * @version 1.0.0
 * @description: TODO
 * @create: 2021-06-13 23:27
 * @author: lang
 */
public class MultiThreadedTest04 {
    public static void main(String[] args) {
        new Thread(()-> System.out.println("Thread is run")).start();
        System.out.println("程序运行结束");
    }
}
