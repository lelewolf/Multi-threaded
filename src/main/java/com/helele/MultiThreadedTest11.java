package com.helele;

/**
 * @version 1.0.0
 * @description: Thread.currentThread表示当前代码段正在被哪个线程调用的相关信息，this表示的是当前对象，与Thread.currentThread有很大的区别。
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest11 {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        final Thread t1 = new Thread(thread);
        t1.setName("A");
        t1.start();
    }

    private static class MyThread extends Thread {

        public MyThread() {
            System.out.println("MyThread----begin");
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
            System.out.println("this.getName() = " + this.getName());
            System.out.println("this.isAlive() = " + this.isAlive());
            System.out.println("MyThread----end");
            // MyThread----begin
            // Thread.currentThread().getName() = main
            // Thread.currentThread().isAlive() = true
            // this.getName() = Thread-0
            // this.isAlive() = false
            // MyThread----end
        }

        @Override
        public void run() {
            System.out.println("MyThread----begin");
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
            System.out.println("this.getName() = " + this.getName());
            System.out.println("this.isAlive() = " + this.isAlive());
            System.out.println("MyThread----end");
            // MyThread----begin
            // Thread.currentThread().getName() = A
            // Thread.currentThread().isAlive() = true
            // this.getName() = Thread-0
            // this.isAlive() = false
            // MyThread----end
        }
    }
}
