package com.example.praticejavaadv.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.example.praticejavaadv.executor.ExecutorUtils.printState;
import static com.example.praticejavaadv.util.MyLogger.log;

@Slf4j
public class ExecutorShutdownMain {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(new RunnableTask("taskA"));
        es.submit(new RunnableTask("taskB"));
        es.submit(new RunnableTask("taskC"));
        es.submit(new RunnableTask("longTask", 100_000));   // 100초 대기
        printState(es);

        log("== shutdown 시작 ==");
        shutdownAndAwaitTermination(es);
        log("== shutdown 완료 ==");
        printState(es);
    }

    private static void shutdownAndAwaitTermination(ExecutorService es) {
        es.shutdown();  // non-blocking, 새로운 작업을 받지 않는다. 처리 중이거나, 큐에 이미 대기 중인 작업은 처리한다. 이후 풀의 스레드들을 정리한다.
        try {
            // 이미 대기 중인 작업들을 모두 완료할 때까지 10초 기다린다.
            if (!es.awaitTermination(10, TimeUnit.SECONDS)) {
                // 정상 종료가 너무 오래걸린다면..
                log("서비스 정상 종료 실패 -> 강제 종료 시도");
                es.shutdownNow();

                // 작업이 취소될 때까지 대기한다.
                if (!es.awaitTermination(10, TimeUnit.SECONDS)) {
                    log("서비스가 종료되지 않았습니다.");
                }
            }
        } catch (InterruptedException e) {
            // awaitTermination() 으로 대기 중인 현재 스레드가 인터럽트 될 수 있다.
            es.shutdownNow();
        }
    }
}
