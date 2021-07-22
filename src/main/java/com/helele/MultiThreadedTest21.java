package com.helele;

/**
 * @version 1.0.0
 * @description: 使用 stop 方法可能会造成一些请理性工作没有完成，或者导致锁定对象的解锁，造成数据不一致；
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest21 {
    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();
    }

    private static class MyThread extends Thread {

        @Override
        public void run() {
            try {
                this.stop();
            } catch (ThreadDeath e) {
                System.out.println("进入 catch");
                e.printStackTrace();
            }
        }
    }
}
