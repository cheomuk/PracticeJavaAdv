package com.example.praticejavaadv.interrupt;

import static com.example.praticejavaadv.util.MyLogger.log;
import static com.example.praticejavaadv.util.ThreadUtils.sleep;

public class ThreadStopMainV2 {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        thread.start();

        sleep(4000);
        log("작업 중단 지시 thread.interrupt()");
        thread.interrupt(); // 거의 지시하자 마자 interrupt 가 터지는 것을 확인할 수 있다.
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {

            try {
                while (true) {
                    log("작업 중");
                    Thread.sleep(3000); // 이 부분에서 interrupt 예외가 발생한다.
                }
            } catch (InterruptedException e) {
                log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
                // interrupt 를 통해 깨우면 state 는 바로 false 로 바뀐다.
                log("interrupted message = " + e.getMessage());
                log("state = " + Thread.currentThread().getState());
                // sleep 에서 깨어나서 RUNNABLE 상태가 된 것을 확인할 수 있다.
            }

            log("자원 정리");
            log("자원 종료");
        }
    }
}
