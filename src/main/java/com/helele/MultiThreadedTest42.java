package com.helele;


/**
 * @version 1.0.0
 * @description: 通过管道进行线程间的通信
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest42 {
    public static void main(String[] args) throws InterruptedException {
        final ThreadA threadA = new ThreadA();
        final ThreadB threadB = new ThreadB();
        threadA.start();
        threadB.start();
        for (int i = 0; i < 100; i++) {
            Tools.t1.set("Main value " + (i + 1));
            System.out.println("main get value " + Tools.t1.get());
            Thread.sleep(200);
        }
    }

    public static class Tools {
        private static ThreadLocal t1 = new ThreadLocal();
    }

    private static class ThreadA extends Thread {

        @Override
        public void run() {

            try {
                for (int i = 0; i < 100; i++) {
                    Tools.t1.set("ThreadA value " + (i + 1));
                    System.out.println("ThreadA get value " + Tools.t1.get());
                }
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ThreadB extends Thread {

        @Override
        public void run() {

            try {
                for (int i = 0; i < 100; i++) {
                    Tools.t1.set("ThreadB value " + (i + 1));
                    System.out.println("ThreadB get value " + Tools.t1.get());
                }
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


