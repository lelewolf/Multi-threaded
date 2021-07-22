package com.helele;


/**
 * @version 1.0.0
 * @description: 通过管道进行线程间的通信
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest41 {
    public static void main(String[] args) throws InterruptedException {
        final ThreadB b = new ThreadB();
        b.start();
        Thread.sleep(500);
        final ThreadC c = new ThreadC(b);
        c.start();
    }


    private static class ThreadA extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                String str = new String();
                Math.random();
            }
        }
    }

    private static class ThreadB extends Thread {

        @Override
        public void run() {
            try {
                final ThreadA threadA = new ThreadA();
                threadA.start();
                threadA.join();
                System.out.println("线程 b 在 run end 处打印");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("线程 b 在catch 处出打印");
            }
        }
    }

    private static class ThreadC extends Thread {

        private ThreadB b;

        public ThreadC(ThreadB b) {
            this.b = b;
        }

        @Override
        public void run() {
           b.interrupt();
        }
    }
}


