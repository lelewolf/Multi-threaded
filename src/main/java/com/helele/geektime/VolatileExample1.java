package com.helele.geektime;

/**
 * @version 1.0.0
 * @description: TODO
 * @create: 2021-06-26 11:20
 * @author: lang
 */
public class VolatileExample1 {

    private static volatile Boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        final Thread thread01 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 开始死循环了");
            while (flag) {
            }
            System.out.println(Thread.currentThread().getName() + " 退出死循环了");
        });
        thread01.start();
        Thread.sleep(1000);
        final Thread thread02 = new Thread(() -> {
            flag = false;
            System.out.println(Thread.currentThread().getName() + " 修改了 flag");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 结束了");
        });
        thread02.start();
    }
}
