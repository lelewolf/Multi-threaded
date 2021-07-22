package com.helele;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

/**
 * @version 1.0.0
 * @description: 使用suspend（）极易造成独占，使得其他线程无法访问公共对象，这里例子中main end没有办法打印，由于线程暂停时在println内部，println方法是同步方法，并且锁未释放
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest27 {
    public static void main(String[] args) throws InterruptedException {
        User user = new User();
        MyThread thread = new MyThread(user);
        thread.setName("a");
        thread.start();
        Thread.sleep(500);
        new Thread(() -> user.print()).start();
    }


    private static class MyThread extends Thread {

        private User user;

        public MyThread(User user) {
            super();
            this.user = user;
        }

        @Override
        public void run() {
            user.setValue("a", "aa");
        }
    }

    private static class User {

        private String username = "1";

        private String password = "11";

        public void setValue(String u, String p) {
            this.username = u;
            if (Thread.currentThread().getName().equals("a")) {
                System.out.println("暂停 a 线程");
                Thread.currentThread().suspend();
            }
            this.password = p;
        }

        public void print() {
            System.out.println("username =" + username + " password =" + password);
        }
    }
}
