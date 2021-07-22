package com.helele;


/**
 * @version 1.0.0
 * @description: 生产者消费者模型中需要使用notifyAll()方法，不然可能会出现唤醒同类线程，导致程序假死的情况发生
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest37 {
    public static void main(String[] args) throws InterruptedException {
        final String lock = new String("");
        final Producer producer = new Producer(lock);
        final Customer customer = new Customer(lock);
        ThreadP[] pThread = new ThreadP[2];
        ThreadC[] cThread = new ThreadC[2];
        for (int i = 0; i < 2; i++) {
            pThread[i] = new ThreadP(producer);
            pThread[i].setName("生产者" + (i + 1));
            cThread[i] = new ThreadC(customer);
            cThread[i].setName("消费者" + (i + 1));
            pThread[i].start();
            cThread[i].start();
        }
        Thread.sleep(5000);
        Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadArray);
        for (int i = 0; i < threadArray.length; i++) {
            System.out.println(threadArray[i].getName() + " ---- " + threadArray[i].getState());
        }
    }

    public static class ValueObject {
        public static String value = "";
    }

    private static class Producer {

        private String lock;

        public Producer(String lock) {
            this.lock = lock;
        }

        public void setValue() {
            synchronized (lock) {
                try {
                    while (!ValueObject.value.equals("")) {
                        System.out.println("生产者 " + Thread.currentThread().getName() + " WAITING @了");
                        lock.wait();
                    }
                    String value = System.currentTimeMillis() + "_" + System.nanoTime();
                    ValueObject.value = value;
                    System.out.println("生产者 " + Thread.currentThread().getName() + " RUNNABLE 了 生成了 vaule = " + value);
                    lock.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Customer {

        private String lock;

        public Customer(String lock) {
            this.lock = lock;
        }

        public void getValue() {
            synchronized (lock) {
                try {
                    while (ValueObject.value.equals("")) {
                        System.out.println("消费者 " + Thread.currentThread().getName() + " WAITING @了");
                        lock.wait();
                    }
                    System.out.println("消费者 " + Thread.currentThread().getName() + " RUNNABLE 了");
                    ValueObject.value = "";
                    lock.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ThreadP extends Thread {

        private Producer p;

        public ThreadP(Producer p) {
            this.p = p;
        }

        @Override
        public void run() {
            p.setValue();
        }
    }

    private static class ThreadC extends Thread {

        private Customer c;

        public ThreadC(Customer c) {
            this.c = c;
        }

        @Override
        public void run() {
            c.getValue();
        }
    }
}

