package com.helele.geektime;

/**
 * @version 1.0.0
 * @description: TODO
 * @create: 2021-06-26 11:20
 * @author: lang
 */
public class VolatileExample {

    int i = 1;

    volatile Boolean flag = false;

    private void write() throws InterruptedException {
        i = 42;
        Thread.sleep(1000);
        flag = true;
    }

    private int read() {
        if (flag == true) {
            return i;
        }
        return i;
    }

    public static int cale() {
        final VolatileExample example = new VolatileExample();
        new Thread(() -> {
            try {
                example.write();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> example.read()).start();
        return example.i;
    }

    public static void main(String[] args) {
        System.out.println(cale());//42
    }
}
