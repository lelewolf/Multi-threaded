package com.helele;

/**
 * @version 1.0.0
 * @description: 不共享数据的情况
 * @create: 2021-06-13 23:47
 * @author: lang
 */
public class MultiThreadedTest05 {
    public static void main(String[] args) {
        final MyThread threadA = new MyThread("a");
        final MyThread threadB = new MyThread("b");
        final MyThread threadC = new MyThread("c");
        threadA.start();
        threadB.start();
        threadC.start();
    }

    private static class MyThread extends Thread {

        private int count = 5;

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (count > 0) {
                count--;
                System.out.println("由 " + Thread.currentThread().getName() + " 计算的到 count = " + count);
            }
        }
    }
}
