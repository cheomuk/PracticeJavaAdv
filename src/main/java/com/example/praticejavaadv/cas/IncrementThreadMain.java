package com.example.praticejavaadv.cas;

import java.util.ArrayList;
import java.util.List;

import static com.example.praticejavaadv.util.MyLogger.log;
import static com.example.praticejavaadv.util.ThreadUtils.sleep;

public class IncrementThreadMain {

    public static final int THREAD_COUNT = 1000;

    public static void main(String[] args) throws InterruptedException {
        test(new BasicInteger());   // 원자적인 계산이 아니었으므로 당연히 1000이 나오지 않는다!
        test(new VolatileInteger());    // volatile 을 붙여도 똑같다. 연산 자체가 나누어져 있기 때문에 효과가 없는 것이다.
        test(new SyncInteger());    // 드디어 1000이 나온다. (임계 영역 구축)
        test(new MyAtomicInteger());    // 여러 스레드가 해당 값을 공유해야 할 때 사용하면 좋다.
    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                sleep(10);  // 너무 빨리 실행되기 때문에, 다른 스레드와 동시 실행을 위해 잠깐 쉬었다가 실행.
                incrementInteger.increment();
            }
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = incrementInteger.get();
        log(incrementInteger.getClass().getSimpleName() + " result: " + result);
    }
}
