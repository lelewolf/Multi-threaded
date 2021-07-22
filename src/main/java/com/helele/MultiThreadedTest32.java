package com.helele;


/**
 * @version 1.0.0
 * @description: 同步异步代码块
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest32 {
    public static void main(String[] args) throws InterruptedException {
        final Task task = new Task();
        TestThread thread01 = new TestThread(task);
        TestThread thread02 = new TestThread(task);
        thread01.start();
        thread02.start();
    }

    private static class Task {
        public void doLongTimeTask() {
            for (int i = 0; i < 100; i++) {
                System.out.println("Thread Name is " + Thread.currentThread().getName() + " i = " + i);
            }
            synchronized (this) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Thread Name is " + Thread.currentThread().getName() + " i = " + i);
                }
            }
        }
    }

    private static class TestThread extends Thread {

        private Task task;

        public TestThread(Task task) {
            super();
            this.task = task;
        }

        @Override
        public void run() {
            task.doLongTimeTask();
        }
    }
}
