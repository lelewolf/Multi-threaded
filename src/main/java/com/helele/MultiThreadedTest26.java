package com.helele;

/**
 * @version 1.0.0
 * @description: 使用suspend（）极易造成独占，使得其他线程无法访问公共对象，这里例子中main end没有办法打印，由于线程暂停时在println内部，println方法是同步方法，并且锁未释放
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest26 {
    public static void main(String[] args) throws InterruptedException {
        final MyThread thread = new MyThread();
        thread.start();
        Thread.sleep(1000);
        thread.suspend();
        System.out.println("main end");
    }


    private static class MyThread extends Thread {

        private long i = 0;

        @Override
        public void run() {
            while (true) {
                i++;
                System.out.println("i = " + i);
            }
        }
    }
}
