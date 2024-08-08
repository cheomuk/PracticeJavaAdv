package com.example.praticejavaadv.control.join;

import static com.example.praticejavaadv.util.MyLogger.log;
import static com.example.praticejavaadv.util.ThreadUtils.sleep;

public class JoinMainV0 {

    public static void main(String[] args) {
        log("Start");

        Thread thread1 = new Thread(new Job(), "Thread1");
        Thread thread2 = new Thread(new Job(), "Thread1");

        thread1.start();
        thread2.start();

        log("end");
    }

    static class Job implements Runnable {

        @Override
        public void run() {
            log("작업 시작");
            sleep(2000);
            log("작업 완료");
        }
    }
}
