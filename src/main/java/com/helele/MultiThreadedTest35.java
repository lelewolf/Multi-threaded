package com.helele;


import java.sql.SQLOutput;

/**
 * @version 1.0.0
 * @description: volatile解决的是可见性问题，不是原子性问题，也就不是同步的
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest35 {
    public static void main(String[] args) throws InterruptedException {
        MyThread[] myThread = new MyThread[100];
        for (int i = 0; i < 100; i++) {
            myThread[i] = new MyThread();
        }
        for (int i = 0; i < 100; i++) {
            myThread[i].start();
        }
    }

    private static class MyThread extends Thread {

        volatile private static int count;

        public static void addcount() {
            for (int i = 0; i < 100; i++) {
                count++;
            }
            System.out.println("count = " + count);
        }

        @Override
        public void run() {
            addcount();
        }
    }
}
