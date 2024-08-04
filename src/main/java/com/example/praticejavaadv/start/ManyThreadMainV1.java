package com.example.praticejavaadv.start;

import static com.example.praticejavaadv.util.MyLogger.log;

public class ManyThreadMainV1 {

    public static void main(String[] args) {    // 다중 스레드 실행
        log("main() start");

        HelloRunnable runnable = new HelloRunnable();

        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        Thread thread3 = new Thread(runnable);
        thread3.start();

        log("main() end");
    }
}
