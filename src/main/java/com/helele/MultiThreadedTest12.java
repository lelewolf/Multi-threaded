package com.helele;

/**
 * @version 1.0.0
 * @description: Thread.currentThread表示当前代码段正在被哪个线程调用的相关信息，this表示的是当前对象，与Thread.currentThread有很大的区别。
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest12 {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        System.out.println("begin " +System.currentTimeMillis());
        thread.start();
        System.out.println("end " +System.currentTimeMillis());
    }

    private static class MyThread extends Thread {

        @Override
        public void run() {
            try {
                System.out.println("run threadName is " + Thread.currentThread().getName()+" begin " +System.currentTimeMillis());
                Thread.sleep(1000);
                System.out.println("run threadName is " + Thread.currentThread().getName()+" end " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
