package com.helele;

/**
 * @version 1.0.0
 * @description: 使用 stop 方法可能会造成一些请理性工作没有完成，或者导致锁定对象的解锁，造成数据不一致；
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest23 {
    public static void main(String[] args) throws InterruptedException {
        final MyThread thread = new MyThread();
        thread.start();
        Thread.sleep(500);
        thread.interrupt();

    }

    private static class MyThread extends Thread {

        @Override
        public void run() {
            while (true) {
                if(this.isInterrupted()){
                    System.out.println("停止了");
                    return;
                }
                System.out.println(System.currentTimeMillis());
            }
        }
    }

}
