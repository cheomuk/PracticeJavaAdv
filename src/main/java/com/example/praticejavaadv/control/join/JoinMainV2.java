package com.example.praticejavaadv.control.join;

import static com.example.praticejavaadv.util.MyLogger.log;
import static com.example.praticejavaadv.util.ThreadUtils.sleep;

public class JoinMainV2 {

    public static void main(String[] args) {
        log("Start");

        SumTask sumTask1 = new SumTask(0, 50);
        SumTask sumTask2 = new SumTask(51, 100);

        Thread thread1 = new Thread(sumTask1, "Thread-1");
        Thread thread2 = new Thread(sumTask2, "Thread-2");

        thread1.start();
        thread2.start();

        // 정확한 타이밍을 맞추어 기다리기 어렵다.-> 대기 시간에도 손해보고 예측 불가능한 계산에선 사용 불가능!
        log("main 스레드 sleep()");
        sleep(3000);
        log("main 스레드 깨어남");

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