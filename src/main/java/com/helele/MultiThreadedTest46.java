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
public class MultiThreadedTest46 {
    public static void main(String[] args) throws InterruptedException {
        final Service service = new Service();
        final ThreadA a = new ThreadA(service);
        final ThreadB b = new ThreadB(service);
        a.start();
        b.start();
    }


    private static class Service {

        private Lock lock = new ReentrantLock();

        private Condition condition = lock.newCondition();

        private boolean hasvalue = false;

        public void get() {
            try {
                lock.lock();
                while (hasvalue == false) {
                    condition.await();
                }
                System.out.println("打印☆☆");
                hasvalue = false;
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }


        public void set() {
            try {
                lock.lock();
                while (hasvalue == true) {
                    condition.await();
                }
                System.out.println("打印☆");
                hasvalue = true;
                condition.signal();

            } catch (InterruptedException e) {
                e.printStackTrace();
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
            while (true) {
                service.get();
            }
        }
    }

    private static class ThreadB extends Thread {

        private Service service;

        public ThreadB(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            while (true) {
                service.set();
            }
        }
    }
}


