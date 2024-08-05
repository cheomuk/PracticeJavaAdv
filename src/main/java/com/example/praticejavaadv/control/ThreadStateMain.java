package com.example.praticejavaadv.control;

import static com.example.praticejavaadv.util.MyLogger.log;

public class ThreadStateMain {

    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable(), "myThread");
        log("myThread.state1 = " + thread.getState());
        log("myThread.start");
        thread.start();
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            log("start");
        }
    }
}
