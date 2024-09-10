package com.example.praticejavaadv.executor.poolsize;

import com.example.praticejavaadv.executor.RunnableTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

import static com.example.praticejavaadv.executor.ExecutorUtils.printState;
import static com.example.praticejavaadv.util.MyLogger.log;
import static com.example.praticejavaadv.util.ThreadUtils.sleep;

@Slf4j
public class PoolSizeMainV1 {

    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ExecutorService es = new ThreadPoolExecutor(2, 4, 3000,
                TimeUnit.MILLISECONDS, workQueue);
        printState(es);

        es.execute(new RunnableTask("task1"));
        printState(es, "task1");

        es.execute(new RunnableTask("task2"));
        printState(es, "task2");

        es.execute(new RunnableTask("task3"));
        printState(es, "task3");

        es.execute(new RunnableTask("task4"));
        printState(es, "task4");

        es.execute(new RunnableTask("task5"));  // 여기서부터 큐와 맥시멈 모두 다 찼는데도 들어오면 스레드가 증가한다.
        printState(es, "task5");

        es.execute(new RunnableTask("task6"));
        printState(es, "task6");

        try {
            es.execute(new RunnableTask("task7"));  // 풀도 max, 큐도 max, 맥시멈도 다 찬 상태에서 또 들어옴.
        } catch (RejectedExecutionException e) {    // RejectedExecutionException 발생!
            log("task7 실행 거절 예외 발생 : " + e);
        }

        sleep(3000);    // 작업들이 다 수행되도록 대기
        log("== 작업 수행 완료 ==");
        printState(es);

        sleep(3000);    // 작업들이 다 수행되도록 대기
        log("== maximumPoolSize 대기 시간 초과 ==");  // 위에서 설정한 3초간 대기 시간이 초과되면 증가된 스레드들이 종료된다.
        printState(es);
    }
}
