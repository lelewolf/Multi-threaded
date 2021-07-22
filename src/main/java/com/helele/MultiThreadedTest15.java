package com.helele;

/**
 * @version 1.0.0
 * @description: interrupted()获得并重置当前线程的线程中断码
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest15 {
    public static void main(String[] args) throws InterruptedException {
        // Thread thread = new Thread(() ->{
        //     for (int i = 0; i < 50; i++) {
        //         System.out.println("i = "+ (i + 1));
        //     }
        // });
        // thread.start();
        // Thread.sleep(1000);
        // thread.interrupt();
        // System.out.println("是否停止？ " +thread.interrupted());false
        // System.out.println("是否停止？ " +thread.interrupted());false

        Thread.currentThread().interrupt();
        System.out.println("是否停止？ " +Thread.interrupted());//true
        System.out.println("是否停止？ " +Thread.interrupted());//false


    }

}
