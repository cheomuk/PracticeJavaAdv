package com.example.praticejavaadv.start.test;

import static com.example.praticejavaadv.util.MyLogger.log;

public class StartTest3 {   // 익명 클래스로 구현

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    log("value: " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, "counter");

        thread.start();
    }
}
