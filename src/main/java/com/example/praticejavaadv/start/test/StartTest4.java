package com.example.praticejavaadv.start.test;

import static com.example.praticejavaadv.util.MyLogger.log;

public class StartTest4 {   // 익명 클래스로 구현

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                log("A");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Thread-A");

        Thread thread2 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                log("B");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Thread-B");

        thread1.start();
        thread2.start();
    }
}
