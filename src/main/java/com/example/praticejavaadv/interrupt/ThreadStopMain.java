package com.example.praticejavaadv.interrupt;

import static com.example.praticejavaadv.util.MyLogger.log;
import static com.example.praticejavaadv.util.ThreadUtils.sleep;

public class ThreadStopMain {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        thread.start();

        sleep(4000);
        log("작업 중단 지시 runFlag = false");
        myTask.runFlag = false; // 중단 지시가 내려져도 바로 중단되지 않는다.
    }

    static class MyTask implements Runnable {

        volatile boolean runFlag = true;

        @Override
        public void run() {

            while (runFlag) {
                log("작업 중");
                sleep(3000);
            }

            log("자원 정리");
            log("자원 종료");
        }
    }
}
