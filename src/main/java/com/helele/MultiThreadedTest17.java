package com.helele;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * @version 1.0.0
 * @description: interrupted()获得并重置当前线程的线程中断码
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest17 {
    public static void main(String[] args) throws InterruptedException {
        final MyThread thread = new MyThread();
        thread.start();
        Thread.sleep(1);
        thread.interrupt();


    }

    private static class MyThread extends Thread {

        // @Override
        // public void run() {
        //     for (int i = 0; i < 500; i++) {
        //         if (this.isInterrupted()) {
        //             System.out.println("我要退出了");
        //             break;
        //         }
        //         System.out.println("i = " + (i + 1));
        //     }
        //     System.out.println("我被输出了，证明还没有停止");
        // }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 500; i++) {
                    if (Thread.interrupted()) {
                        System.out.println(this.isInterrupted());
                        System.out.println("我要退出了");
                        throw new InterruptedException();
                    }
                    System.out.println("i = " + (i + 1));
                }
                System.out.println("我被输出了，证明还没有停止");
            } catch (InterruptedException e) {
                System.out.println("进入" + MyThread.class.getName() + "类的 catch 了");
                e.printStackTrace();
            }
        }
    }
}
