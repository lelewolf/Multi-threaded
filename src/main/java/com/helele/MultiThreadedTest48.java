package com.helele;


import java.util.concurrent.locks.StampedLock;

/**
 * @version 1.0.0
 * @description: 多条件使用Condition进行唤醒
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest48 {
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
        private StampedLock lock = new StampedLock();

        private int x = 70;

        private int y = 20;

        public double read() {
            double xValue = 0;
            double yValue = 0;
            long stamp = lock.tryOptimisticRead();
            try {
                System.out.println("获得乐观读锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                xValue = x;
                Thread.sleep(1000);
                yValue = y;
                if (!lock.validate(stamp)) {
                    System.out.println("获取悲观读锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                    stamp = lock.readLock();//获取悲观读锁
                    xValue = x;
                    yValue = y;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(stamp);//释放悲观读锁
                System.out.println("释放悲观读锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            }

            return Math.sqrt(xValue + yValue);
        }

        public void write() {
            long stamp = lock.writeLock();//获取写锁
            try {
                x += x;
                y += y;
                System.out.println("获得写锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            } finally {
                lock.unlockWrite(stamp);//释放写锁
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
            System.out.println("结果是 + " + service.read());
        }
    }

    private static class ThreadB extends Thread {

        private Service service;

        public ThreadB(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.write();
        }
    }
}


