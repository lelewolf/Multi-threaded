package com.helele;

import javax.swing.*;

/**
 * @version 1.0.0
 * @description: 共享数据，造成线程安全问题
 * @create: 2021-06-13 23:53
 * @author: lang
 */
public class MultiThreadedTest06 {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        Thread thread1 = new Thread(thread, "A");
        Thread thread2 = new Thread(thread, "B");
        Thread thread3 = new Thread(thread, "C");
        Thread thread4 = new Thread(thread, "D");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }

    private static class MyThread extends Thread {

        private int count = 5;

        @Override
        public synchronized void run() {
            // synchronized (this) {
                count--;
                System.out.println("由 " + Thread.currentThread().getName() + " 计算得到 count = " + count);
            // }
        }
    }
}
