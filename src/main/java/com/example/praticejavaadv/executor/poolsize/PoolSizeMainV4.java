package com.example.praticejavaadv.executor.poolsize;

import com.example.praticejavaadv.executor.RunnableTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

import static com.example.praticejavaadv.executor.ExecutorUtils.printState;
import static com.example.praticejavaadv.util.MyLogger.log;
import static com.example.praticejavaadv.util.ThreadUtils.sleep;

@Slf4j
public class PoolSizeMainV4 {

    // private static final int TASK_SIZE = 1100;  // 1. 일반
    private static final int TASK_SIZE = 1200;   // 2. 긴급 -> 맥시멈 pool 사용
    // private static final int TASK_SIZE = 1300;   // 3. 거절 -> 큐와 맥시멈 이상으로 넘어가면 Rejected 발생시키고 버린다!

    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(100, 200, 60,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));
        printState(es);

        long startMs = System.currentTimeMillis();
        for (int i = 1; i <= TASK_SIZE; i++) {
            String taskName = "task" + i;
            try {
                es.execute(new RunnableTask(taskName));
                printState(es, taskName);
            } catch (RejectedExecutionException e) {
                log(taskName + " -> " + e);
            }
        }

        es.close();

        long endMs = System.currentTimeMillis();
        log("time : " + (endMs - startMs));
    }
}
