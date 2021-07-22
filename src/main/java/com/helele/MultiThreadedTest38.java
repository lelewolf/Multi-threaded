package com.helele;


import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0.0
 * @description: 单一线程的生产者消费者模式
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest38 {
    public static void main(String[] args) throws InterruptedException {
        final MyStack stack = new MyStack();
        final PopThread popThread01 = new PopThread(stack);
        final PopThread popThread02 = new PopThread(stack);
        final PopThread popThread03 = new PopThread(stack);
        final PushThread pushThread01 = new PushThread(stack);
        final PushThread pushThread02 = new PushThread(stack);
        final PushThread pushThread03 = new PushThread(stack);
        pushThread01.start();
        pushThread02.start();
        pushThread03.start();
        popThread01.start();
        popThread02.start();
        popThread03.start();
    }


    private static class MyStack {

        private List<String> list = new ArrayList();

        synchronized public void push() {
            try {
                while (list.size() == 1) {
                    this.wait();
                }
                list.add("anyString= " + Math.random());
                this.notifyAll();
                System.out.println("push之后 size=" + list.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized public String pop() {
            String value = "";
            try {
                while (list.size() == 0) {
                    System.out.println("pop 操作中的：" + Thread.currentThread().getName() + " 线程 WAIT 状态");
                    this.wait();
                }
                value = list.get(0);
                list.remove(0);
                this.notifyAll();
                System.out.println("pop的是" + value);
                System.out.println("pop之后 size=" + list.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return value;
        }

    }

    private static class PopThread extends Thread {

        private MyStack stack;

        public PopThread(MyStack stack) {
            this.stack = stack;
        }

        @Override
        public void run() {
            while (true) {
                stack.pop();
            }
        }
    }

    private static class PushThread extends Thread {

        private MyStack stack;

        public PushThread(MyStack stack) {
            this.stack = stack;
        }

        @Override
        public void run() {
            while (true) {
                stack.push();
            }
        }
    }
}

