package com.helele;

import java.util.concurrent.SynchronousQueue;

/**
 * @version 1.0.0
 * @description: 使用 stop 方法可能会造成一些请理性工作没有完成，或者导致锁定对象的解锁，造成数据不一致；
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest25 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread01 = new Thread(() -> MultiThreadedTest25.printString());
        thread01.setName("a");
        thread01.start();
        Thread.sleep(1000);
        Thread thread02 = new Thread(() -> System.out.println("线程b 启动了，但是进入不了printString方法，因为方法被 a 线程永远暂停独占了"));
        thread02.start();
    }

    synchronized public static void printString() {
        System.out.println("Begin");
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("a 线程永远 suspend 了");
            Thread.currentThread().suspend();
        }
    }
}
