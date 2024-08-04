package com.example.praticejavaadv.start;

import static com.example.praticejavaadv.util.MyLogger.log;

public class InnerRunnableMainV2 {

    public static void main(String[] args) {
        log("main start()");

        Runnable runnable = new Runnable() {    // 익명 클래스

            @Override
            public void run() {
                log("run()");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        log("main end()");
    }
}
