package com.helele;


/**
 * @version 1.0.0
 * @description: 死锁问题以及分析
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest34 {
    public static void main(String[] args) throws InterruptedException {
        final DealLockTest lockTest = new DealLockTest();
        lockTest.setUsername("a");
        Thread t1 = new Thread(lockTest);
        t1.start();
        Thread.sleep(100);
        lockTest.setUsername("b");
        final Thread t2 = new Thread(t1);
        t2.start();
    }

    private static class DealLockTest implements Runnable {

        private String username;

        private Object lock1 = new Object();

        private Object lock2 = new Object();

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public void run() {
            if (username.equals("a")) {
                synchronized (lock1) {
                    try {
                        System.out.println("username " + username);
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2) {
                        System.out.println("lock1 ->lock2代码顺序执行了");
                    }
                }
            }
            if (username.equals("b")) {
                synchronized (lock2) {
                    try {
                        System.out.println("username " + username);
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1) {
                        System.out.println("lock2 ->lock1代码顺序执行了");
                    }
                }
            }
        }
    }


}
