package com.example.praticejavaadv.start;

import static com.example.praticejavaadv.util.MyLogger.log;

public class InnerRunnableMainV3 {

    public static void main(String[] args) {
        log("main start()");

        // 인라인 익명 클래스 -> 람다로 변경
        Thread thread = new Thread(() -> log("run()"));
        thread.start();

        log("main end()");
    }
}
