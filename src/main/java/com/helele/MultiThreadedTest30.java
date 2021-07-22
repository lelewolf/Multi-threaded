package com.helele;

/**
 * @version 1.0.0
 * @description: 当没有用户线程的时候，jvm 就停止了
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest30 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    System.out.println("i = " + (i++));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(5000);
        System.out.println("当没有用户线程的时候，jvm 就停止了");
    }

}
