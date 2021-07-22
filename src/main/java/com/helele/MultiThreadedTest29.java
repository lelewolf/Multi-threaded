package com.helele;

/**
 * @version 1.0.0
 * @description: 线程优先级的传递性
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest29 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main thread Priority is " + Thread.currentThread().getPriority());
        // Thread.currentThread().setPriority(6);
        new Thread(() -> {
            System.out.println("线程 1 的优先级 " + Thread.currentThread().getPriority());
            new Thread(() -> {
                System.out.println("线程 2 的优先级 " + Thread.currentThread().getPriority());

            }).start();
        }).start();
    }

}
