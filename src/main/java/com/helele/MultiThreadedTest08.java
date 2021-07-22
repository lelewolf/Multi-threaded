package com.helele;

/**
 * @version 1.0.0
 * @description: TODO
 * @create: 2021-06-14 11:46
 * @author: lang
 */
public class MultiThreadedTest08 {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        // thread.start();
        //构造方法由 main
        // run 方法由 Thread-0
        thread.run();
        //构造方法由 main
        // run 方法由 main
    }

    private static class MyThread extends Thread {

        public MyThread() {
            System.out.println("构造方法由 "+ Thread.currentThread().getName());
        }

        @Override
        public void run() {
            System.out.println("run 方法由 " +Thread.currentThread().getName());
        }
    }
}
