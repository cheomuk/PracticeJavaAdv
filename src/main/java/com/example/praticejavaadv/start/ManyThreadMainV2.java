package com.example.praticejavaadv.start;

import static com.example.praticejavaadv.util.MyLogger.log;

public class ManyThreadMainV2 {

    public static void main(String[] args) {    // 다중 스레드 실행2
        log("main() start");

        HelloRunnable runnable = new HelloRunnable();

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }

        log("main() end");
    }
}
