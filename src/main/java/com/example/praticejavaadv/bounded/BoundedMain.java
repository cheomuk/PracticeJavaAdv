package com.example.praticejavaadv.bounded;

import java.util.ArrayList;
import java.util.List;

import static com.example.praticejavaadv.util.MyLogger.log;
import static com.example.praticejavaadv.util.ThreadUtils.sleep;

public class BoundedMain {

    public static void main(String[] args) {
        // 1. BoundedQueue 선택
        // BoundedQueue queue = new BoundedQueueV1(2);
        // BoundedQueue queue = new BoundedQueueV2(2); // 락을 갖고 sleep 에 들어갔기 때문에 이후 스레드들은 다 BLOCKED 상태에 빠진다.
        // BoundedQueue queue = new BoundedQueueV3(2);
        // BoundedQueue queue = new BoundedQueueV4(2);
        BoundedQueue queue = new BoundedQueueV5(2);

        // 2. 생산자, 소비자 실행 순서 선택, 반드시 하나만 선택!
        producerFirst(queue);   // 생산자 먼저 실행
        // consumerFirst(queue);   // 소비자 먼저 실행
    }

    private static void producerFirst(BoundedQueue queue) {
        log("== [생산자 먼저 실행] 시작, " + queue.getClass().getSimpleName() + "==");
        List<Thread> threads = new ArrayList<>();
        startProducer(queue, threads);
        printAllState(queue, threads);
        startConsumer(queue, threads);
        printAllState(queue, threads);
        log("== [생산자 먼저 종료] 시작, " + queue.getClass().getSimpleName() + "==");
    }

    private static void consumerFirst(BoundedQueue queue) {
        log("== [소비자 먼저 실행] 시작, " + queue.getClass().getSimpleName() + "==");
        List<Thread> threads = new ArrayList<>();
        startConsumer(queue, threads);
        printAllState(queue, threads);
        startProducer(queue, threads);
        printAllState(queue, threads);
        log("== [소비자 먼저 종료] 시작, " + queue.getClass().getSimpleName() + "==");
    }

    private static void startProducer(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("생산자 시작");
        for (int i = 1; i <= 3; i++) {
            Thread producer = new Thread(new ProducerTask(queue, "data" + i), "producer" + i);
            threads.add(producer);
            producer.start();
            sleep(100); // 예제를 쉽게 확인하기 위해 잠깐 슬립
        }
    }

    private static void printAllState(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("현재 상태 출력, 큐 데이터 : " + queue);
        for (Thread thread : threads) {
            log(thread.getName() + ": " + thread.getState());
        }
    }

    private static void startConsumer(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("소비자 시작");
        for (int i = 1; i <= 3; i++) {
            Thread consumer = new Thread(new ConsumerTask(queue), "consumer" + i);
            threads.add(consumer);
            consumer.start();
            sleep(100); // 예제를 쉽게 확인하기 위해 잠깐 슬립
        }
    }
}
