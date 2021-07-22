package com.helele;

/**
 * @version 1.0.0
 * @description: 非线程安全试验
 * @create: 2021-06-14 00:04
 * @author: lang
 */
public class LoginServlet {

    private static String usernameRef;

    private static String passwordRef;

    synchronized public static void login(String username, String password) {
        try {
            usernameRef = username;
            if ("a".equals(username)) {
                Thread.sleep(5000);
            }
            passwordRef = password;
            System.out.println("username = " + usernameRef + " password = " + passwordRef);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> login("a", "aa")).start();
        new Thread(() -> login("b", "bb")).start();
    }
}
