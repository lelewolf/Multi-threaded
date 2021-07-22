package com.helele;

/**
 * @version 1.0.0
 * @description: 发生异常，锁自动释放
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest31 {
    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        TestThread a = new TestThread(service);
        TestThread b = new TestThread(service);
        a.setName("a");
        b.setName("b");
        a.start();
        Thread.sleep(500);
        b.start();
    }

    private static class Service {
        synchronized public void testMethod() {
            if (Thread.currentThread().getName().equals("a")) {
                System.out.println("ThreadName " + Thread.currentThread().getName() + " begin time " + System.currentTimeMillis());
                int i = 1;
                while (i == 1) {
                    if (("" + Math.random()).substring(0, 8).equals("0.123456")) {
                        System.out.println("ThreadName " + Thread.currentThread().getName() + " run exception " + System.currentTimeMillis());
                        Integer.parseInt("a");
                    }
                }
            } else {
                System.out.println("ThreadName b run time " + System.currentTimeMillis());
            }
        }
    }


    private static class TestThread extends Thread {

        private Service service;

        public TestThread(Service service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            super.run();
            service.testMethod();
        }
    }
}
