package com.helele;

/**
 * @version 1.0.0
 * @description: 在 sleep 状态下中断，会进入 catch 语句，并且清除状态值，使之变成 false
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest20 {
    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();
        Thread.sleep(8000);
        thread.stop();
    }

    private static class MyThread extends Thread {

        private int i = 0;

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("i = " + (i++));
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
