package com.example.praticejavaadv.volatile1;

import static com.example.praticejavaadv.util.MyLogger.log;
import static com.example.praticejavaadv.util.ThreadUtils.sleep;

public class VolatileFlagMain {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "myTask");
        log("runFlag = " + myTask.runFlag);
        thread.start();

        sleep(1000);
        log("runFlag 를 false 로 변경 시도");
        myTask.runFlag = false;
        log("runFlag = " + myTask.runFlag);
        log("main 종료"); // 메인은 종료됐지만 myTask 스레드가 while 문을 벗어나지 못하고 계속 실행 중.
    }

    static class MyTask implements Runnable {

        boolean runFlag = true; // 캐시 메모리 값만 변경됨. -> 메인은 종료돼도 myTask 스레드의 캐시 메모리 값은 변경 안 됐기 때문에 계속 실행 중.
        // volatile boolean runFlag = true; // 메인 메모리에 바로 접근해서 읽거나 수정한다.

        @Override
        public void run() {
            log("task 시작");
            while (runFlag) {
                // runFlag 가 false 로 변하면 탈출
            }
            log("task 종료");
        }
    }
}
