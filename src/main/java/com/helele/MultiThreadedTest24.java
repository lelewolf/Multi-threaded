package com.helele;

/**
 * @version 1.0.0
 * @description: 使用 stop 方法可能会造成一些请理性工作没有完成，或者导致锁定对象的解锁，造成数据不一致；
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest24 {
    public static void main(String[] args) throws InterruptedException {
        final MyThread thread = new MyThread();
        thread.start();
        Thread.sleep(500);

        thread.suspend();
        System.out.println("A = " + System.currentTimeMillis() + "i = " + thread.getI());
        Thread.sleep(500);
        System.out.println("A = " + System.currentTimeMillis() + "i = " + thread.getI());

        thread.resume();
        Thread.sleep(500);

        thread.suspend();
        System.out.println("B = " + System.currentTimeMillis() + "i = " + thread.getI());
        Thread.sleep(500);
        System.out.println("B = " + System.currentTimeMillis() + "i = " + thread.getI());
    }

    private static class MyThread extends Thread {
        private long i = 0;

        public long getI() {
            return i;
        }

        public void setI(long i) {
            this.i = i;
        }

        @Override
        public void run() {
            while (true) {
                i++;
            }
        }
    }

}
