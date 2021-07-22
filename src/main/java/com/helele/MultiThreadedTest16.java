package com.helele;

/**
 * @version 1.0.0
 * @description: isInterrupted()获得调用者线程的线程中断码
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest16 {
    public static void main(String[] args) {

        // // try {
        //     Thread thread = new Thread(() ->{
        //         for (int i = 0; i < 500; i++) {
        //             System.out.println("i = "+ (i + 1));
        //         }
        //     });
        //     thread.start();
        //     // Thread.sleep(1000);
        //     thread.interrupt();
        //     System.out.println("是否停止？= " +thread.isInterrupted());
        //     System.out.println("是否停止？= " +thread.isInterrupted());
        // // } catch (InterruptedException e) {
        // //     e.printStackTrace();
        // // }
        // System.out.println("end");

        final Thread thread = Thread.currentThread();
        thread.interrupt();
        System.out.println(thread.isInterrupted());
        System.out.println(thread.isInterrupted());


    }

}
