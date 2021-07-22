package com.helele;


import javax.swing.plaf.synth.SynthButtonUI;

/**
 * @version 1.0.0
 * @description: 同步异步代码块
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest33 {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        final TestThread01 thread01 = new TestThread01(task);
        final TestThread02 thread02 = new TestThread02(task);
        thread01.setName("a");
        thread02.setName("b");
        thread01.start();
        thread02.start();
    }

    private static class Task {

        public void methodA(){
            synchronized (this){
                try {
                    System.out.println("A begin time "+ System.currentTimeMillis());
                    Thread.sleep(5000);
                    System.out.println("A end time "+ System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void methodB(){
            synchronized (this) {
                System.out.println("B begin time "+ System.currentTimeMillis());
                System.out.println("B end time "+ System.currentTimeMillis());
            }
        }
    }

    private static class TestThread01 extends Thread {

        private Task task;

        public TestThread01(Task task) {
            super();
            this.task = task;
        }

        @Override
        public void run() {
            task.methodA();
        }
    }

    private static class TestThread02 extends Thread {

        private Task task;

        public TestThread02(Task task) {
            super();
            this.task = task;
        }

        @Override
        public void run() {
            task.methodB();
        }
    }
}
