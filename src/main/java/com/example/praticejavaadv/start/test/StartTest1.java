package com.example.praticejavaadv.start.test;

import static com.example.praticejavaadv.util.MyLogger.log;

public class StartTest1 {

    public static void main(String[] args) {    // Thread 로 구현
        CounterThread thread = new CounterThread();
        thread.start();
    }

    static class CounterThread extends Thread {

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
    }
}
