package com.helele;


import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.TreeUI;

/**
 * @version 1.0.0
 * @description: 各线程间的数据值没有可视性造成的；
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest36 {
    public static void main(String[] args) throws InterruptedException {
        final Service service = new Service();
        final Thread01 thread01 = new Thread01(service);
        final Thread02 thread02 = new Thread02(service);
        thread01.start();
        Thread.sleep(1000);
        thread02.start();
        System.out.println("已经发出停止命令");
    }

    private static class Service {

        private boolean isContinueRun = true;

        public void runMethod() {
            final String s = new String();
            while (isContinueRun) {
                synchronized (s){}
            }
            System.out.println("停下来了");
        }

        public void stop() {
            isContinueRun = false;
        }
    }


    private static class Thread01 extends Thread {

        private Service service;

        public Thread01(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.runMethod();
        }
    }

    private static class Thread02 extends Thread {

        private Service service;

        public Thread02(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.stop();
        }
    }
}
