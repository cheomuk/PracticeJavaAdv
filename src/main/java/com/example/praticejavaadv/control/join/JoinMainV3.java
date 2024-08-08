package com.example.praticejavaadv.control.join;

import static com.example.praticejavaadv.util.MyLogger.log;
import static com.example.praticejavaadv.util.ThreadUtils.sleep;

public class JoinMainV3 {

    public static void main(String[] args) throws InterruptedException {
        log("Start");

        SumTask sumTask1 = new SumTask(0, 50);
        SumTask sumTask2 = new SumTask(51, 100);

        Thread thread1 = new Thread(sumTask1, "Thread-1");
        Thread thread2 = new Thread(sumTask2, "Thread-2");

        thread1.start();
        thread2.start();

        // 스레드가 종료될 때까지 기다림. -> 해당 작업이 끝날 때까지 무기한으로 기다림(단점)
        log("join() - main 스레드가 thread1, thread2 종료될 때까지 대기");
        thread1.join(); // main 의 상태가 WAITING 이 됨.
        thread2.join(); // thread1 이 끝났으면 RUNNABLE 로 바뀌었다가 thread2 에서 다시 WAITING 이 됨.
        log("main 스레드 대기 완료");  // 스레드들의 작업이 끝나자마자 깨어남. (RUNNABLE)

        log("task.result = " + sumTask1.result);
        log("task.result = " + sumTask2.result);

        int sumAll = sumTask1.result = sumTask2.result;
        log("task1 + task2 = " + sumTask1.result + sumTask2.result);

        log("end");
    }

    static class SumTask implements Runnable {

        int startValue;
        int endValue;
        int result = 0;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            log("작업 시작");
            sleep(2000);

            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }

            result = sum;
            log("작업 완료 result = " + result);
        }
    }
}
