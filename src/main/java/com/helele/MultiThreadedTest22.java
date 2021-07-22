package com.helele;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

/**
 * @version 1.0.0
 * @description: 使用 stop 方法可能会造成一些请理性工作没有完成，或者导致锁定对象的解锁，造成数据不一致；
 * @create: 2021-06-14 11:53
 * @author: lang
 */
public class MultiThreadedTest22 {
    public static void main(String[] args) throws InterruptedException {
        User user = new User();
        final MyThread thread = new MyThread(user);
        thread.start();
        Thread.sleep(500);
        thread.stop();
        System.out.println(user.getUsername() + user.getPassword());
    }

    private static class MyThread extends Thread {
        private User user;

        public MyThread(User user) {
            super();
            this.user = user;
        }

        @Override
        public void run() {
           user.printString("b","bb");
        }
    }

    private static class User {

        private String username = "a";

        private String password = "aa";

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        synchronized public void printString(String username,String password){
            try {
                this.username = username;
                Thread.sleep(100000);
                this.password = password;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
