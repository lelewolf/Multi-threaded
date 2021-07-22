package com.helele;


import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @version 1.0.0
 * @description: 多条件使用Condition进行唤醒
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest47 {
    public static void main(String[] args) throws InterruptedException {
        final Service service = new Service();
        final ThreadA threadA = new ThreadA(service);
        threadA.setName("A");
        final ThreadB threadB = new ThreadB(service);
        threadB.setName("B");
        threadA.start();
        threadB.start();
    }


    private static class Service {
        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        public void read() {
            try {
                lock.writeLock().lock();
                System.out.println("获得读锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
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
            service.read();

        }
    }

    private static class ThreadB extends Thread {

        private Service service;

        public ThreadB(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.read();
        }
    }
}


