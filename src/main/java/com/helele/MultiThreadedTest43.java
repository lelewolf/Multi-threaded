package com.helele;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0.0
 * @description: 通过管道进行线程间的通信
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest43 {
    public static void main(String[] args) throws InterruptedException {
        final Service service = new Service();
        final ThreadA a = new ThreadA(service);
        final ThreadA b = new ThreadA(service);
        final ThreadA c = new ThreadA(service);
        final ThreadA d = new ThreadA(service);
        a.start();
        b.start();
        c.start();
        d.start();
    }


    private static class Service {
        private Lock lock = new ReentrantLock();

        public void test() {
            lock.lock();
            for (int i = 0; i < 20; i++) {
                System.out.println("ThreadName =" + Thread.currentThread().getName() + "   =" +(i + 1));
            }
            lock.unlock();
        }
    }

    private static class ThreadA extends Thread {
        private Service service;

        public ThreadA(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.test();
        }
    }
}


