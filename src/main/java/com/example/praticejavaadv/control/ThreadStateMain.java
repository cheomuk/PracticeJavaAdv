package com.example.praticejavaadv.control;

import static com.example.praticejavaadv.util.MyLogger.log;

public class ThreadStateMain {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyRunnable(), "myThread");
        log("myThread.state1 = " + thread.getState());
        log("myThread.start");
        thread.start();
        Thread.sleep(1000); // MyRunnable 이 sleep 인 상태를 보기 위해 1초만 슬립
        log("myThread.state3 = " + thread.getState());  // MyRunnable 상태 출력 (TIMED_WAITING)
        Thread.sleep(4000);
        log("myThread.state5 = " + thread.getState());  // TERMINATED
        log("end");
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            try {
                log("start");
                log("myThread.state2 = " + Thread.currentThread().getState());
                log("sleep start");
                Thread.sleep(3000);
                log("sleep end");
                log("myThread.state4 = " + Thread.currentThread().getState());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
