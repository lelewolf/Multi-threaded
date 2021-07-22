package com.helele;

/**
 * @version 1.0.0
 * @description: TODO
 * @create: 2021-06-14 12:14
 * @author: lang
 */
public class MultiThreadedTest10 {
    public static void main(String[] args) throws InterruptedException {
        final MyThread thread = new MyThread();
        System.out.println("begin =" + thread.isAlive());
        thread.start();
        Thread.sleep(1000);
        System.out.println("end =" + thread.isAlive());
    }

    private static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("run is " + this.isAlive());
        }
    }
}
