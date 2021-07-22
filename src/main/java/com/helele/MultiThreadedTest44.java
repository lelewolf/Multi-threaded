package com.helele;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0.0
 * @description: 通过管道进行线程间的通信
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest44 {
    public static void main(String[] args) throws InterruptedException {
        final Service service = new Service();
        final ThreadTest test = new ThreadTest(service);
        test.start();
        Thread.sleep(3000);
        service.signal();
    }


    private static class Service {

        private Lock lock = new ReentrantLock();

        private Condition condition = lock.newCondition();

        public void await() {
            try {
                lock.lock();
                System.out.println("await 的时间是" + System.currentTimeMillis());
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void signal() {
            try {
                lock.lock();
                System.out.println("signal 的时间是" + System.currentTimeMillis());
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    private static class ThreadTest extends Thread {

        private Service service;

        public ThreadTest(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.await();
        }
    }
}


