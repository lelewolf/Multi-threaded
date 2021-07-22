package com.helele;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0.0
 * @description: 多条件使用Condition进行唤醒
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest45 {
    public static void main(String[] args) throws InterruptedException {
        final Service service = new Service();
        final ThreadA a = new ThreadA(service);
        final ThreadB b = new ThreadB(service);
        a.setName("A");
        b.setName("B");
        a.start();
        b.start();
        Thread.sleep(3000);
        service.signalAll_A();
    }


    private static class Service {

        private Lock lock = new ReentrantLock();

        private Condition conditionA = lock.newCondition();

        private Condition conditionB = lock.newCondition();

        public void awaitA() {
            try {
                lock.lock();
                System.out.println("awaitA begin time is " + System.currentTimeMillis() + " threadName = " + Thread.currentThread().getName());
                conditionA.await();
                System.out.println("awaitA end time is " + System.currentTimeMillis() + " threadName = " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void awaitB() {
            try {
                lock.lock();
                System.out.println("awaitB begin time is " + System.currentTimeMillis() + " threadName = " + Thread.currentThread().getName());
                conditionB.await();
                System.out.println("awaitB end time is " + System.currentTimeMillis() + " threadName = " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void signalAll_A() {
            try {
                lock.lock();
                System.out.println("signalA 的时间是" + System.currentTimeMillis() + " threadName = " + Thread.currentThread().getName());
                conditionA.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public void signalAll_B() {
            try {
                lock.lock();
                System.out.println("signalB 的时间是" + System.currentTimeMillis() + " threadName = " + Thread.currentThread().getName());
                conditionB.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    private static class ThreadA extends Thread {

        private Service service;

        public ThreadA(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.awaitA();
        }
    }

    private static class ThreadB extends Thread {

        private Service service;

        public ThreadB(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.awaitB();
        }
    }
}


