package com.helele;

/**
 * @version 1.0.0
 * @description: 在 sleep 状态下中断，会进入 catch 语句，并且清除状态值，使之变成 false
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest19 {
    public static void main(String[] args) throws InterruptedException {
        final MyThread thread = new MyThread();
        thread.start();
        thread.interrupt();
        System.out.println("main thread end!");
    }

    private static class MyThread extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {
                    System.out.println("i = " + (i + 1));
                }
                System.out.println("run begin");
                Thread.sleep(20000000);
                System.out.println("run end");
            } catch (InterruptedException e) {
                System.out.println("先停止，再遇到 sleep！。进入 catch");
                e.printStackTrace();
            }
        }
    }
}
