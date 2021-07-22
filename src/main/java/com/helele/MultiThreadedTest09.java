package com.helele;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @version 1.0.0
 * @description: TODO
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest09 {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        final Thread t1 = new Thread(thread);
        t1.setName("A");
        t1.start();
    }

    private static class MyThread extends Thread {

        public MyThread() {
            System.out.println("MyThread----begin");
            System.out.println("threadName = " + Thread.currentThread().getName());
            System.out.println("this.getName = " + this.getName());
            System.out.println("MyThread----end");
            // MyThread----begin
            // threadName = main
            // this.getName = Thread-0
            // MyThread----end
        }

        @Override
        public void run() {
            System.out.println("MyThread----begin");
            System.out.println("threadName = " + Thread.currentThread().getName());
            System.out.println("this.getName = " + this.getName());
            System.out.println("MyThread----end");
            // MyThread----begin
            // threadName = A
            // this.getName = Thread-0
            // MyThread----end
        }
    }
}
