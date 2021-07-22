package com.helele;

import java.util.Random;

/**
 * @version 1.0.0
 * @description: 线程启动顺序和 start（）执行顺序无关
 * @create: 2021-06-13 23:13
 * @author: lang
 */
public class MultiThreadedTest03 {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        int time = (int) (Math.random() * 1000);
                        Thread.sleep(time);
                        System.out.println("run is " + Thread.currentThread().getName());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        for (int i = 0; i < 10; i++) {
            int time = (int) (Math.random() * 1000);
            try {
                Thread.sleep(time);
                System.out.println("run is " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}





