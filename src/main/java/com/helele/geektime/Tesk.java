package com.helele.geektime;

/**
 * @version 1.0.0
 * @description: TODO
 * @create: 2021-06-24 20:31
 * @author: lang
 */
public class Tesk {

    private long count = 5;

    private void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            count += 1;
        }
    }

    public static long cale() throws InterruptedException {
        final Tesk tesk = new Tesk();
        final Thread thread01 = new Thread(() -> tesk.add10K());
        final Thread thread02 = new Thread(() -> tesk.add10K());
        thread01.start();
        thread02.start();
        thread01.join();
        thread02.join();
        return tesk.count;
    }

    public static void main(String[] args) {
        try {
            System.out.println(cale());//15083  15459  15032,多次执行少于 20000
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
