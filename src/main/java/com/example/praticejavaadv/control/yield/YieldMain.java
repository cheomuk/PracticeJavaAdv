package com.example.praticejavaadv.control.yield;

import static com.example.praticejavaadv.util.ThreadUtils.sleep;

public class YieldMain {

    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
                // sleep(1);   // 아무 것도 없었을 때와 다르게 스레드가 엄청 자주 바뀌어서 처리된다. 상태 또한 변경된다.
                Thread.yield();
                // RUNNABLE 상태로 CPU를 다른 스레드에게 양보한다. -> empty 와 sleep 의 중간 사이 정도로 처리됨.
                // 특징은 운영체제에 힌트만 제공할 뿐, 강제적인 실행 순서를 지정하지 않음.
                // 즉, 반드시 다른 스레드가 실행되진 않는다.
            }
        }
    }
}
