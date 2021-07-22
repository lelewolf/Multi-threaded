package com.helele;

/**
 * @version 1.0.0
 * @description: 用时变慢，证明了 yield 方法让出了 cpu 时间
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest28 {
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            long beginTime = System.currentTimeMillis();
            int couunt = 0;
            for (int i = 0; i < 500000; i++) {
                Thread.yield();
                couunt = (i + 1);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("用时： " + (endTime - beginTime));
        }).start();
    }

}
