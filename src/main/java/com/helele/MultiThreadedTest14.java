package com.helele;

/**
 * @version 1.0.0
 * @description: Thread.currentThread表示当前代码段正在被哪个线程调用的相关信息，this表示的是当前对象，与Thread.currentThread有很大的区别。
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest14 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() ->{
            for (int i = 0; i < 500000; i++) {
                System.out.println("i = "+ (i + 1));
            }
        });
        thread.start();
       // System.out.println(Thread.currentThread().getName());//main
        Thread.sleep(2000);
        thread.interrupt();

    }

}
