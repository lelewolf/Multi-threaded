package com.helele;

import javax.print.attribute.standard.OrientationRequested;

/**
 * @version 1.0.0
 * @description: 进入 print（）方法前，先计算 i--，所以可能会造成线程安全问题
 * @create: 2021-06-14 11:35
 * @author: lang
 */
public class MultiThreadedTest07 {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        new Thread(thread).start();
        new Thread(thread).start();
        new Thread(thread).start();
        new Thread(thread).start();
        new Thread(thread).start();

    }

    private static class MyThread extends Thread {

        private int i = 5;

        @Override
        synchronized public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i = " + (i--) + " threadName = " + Thread.currentThread().getName());
        }
    }
}
