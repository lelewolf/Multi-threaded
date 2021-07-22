package com.helele;


/**
 * @version 1.0.0
 * @description: 通过管道进行线程间的通信
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest40 {
    public static void main(String[] args) throws InterruptedException {
        final ThreadTest threadTest = new ThreadTest();
        threadTest.start();
        threadTest.join();
        System.out.println("等待threadTest线程执行完毕后执行");
    }


    private static class ThreadTest extends Thread {

        @Override
        public void run() {
            try {
                int sercondValue = (int) (Math.random() * 10000);
                System.out.println(sercondValue);
                Thread.sleep(sercondValue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


