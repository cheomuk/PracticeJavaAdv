package com.example.praticejavaadv.executor.poolsize;

import com.example.praticejavaadv.executor.RunnableTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

import static com.example.praticejavaadv.executor.ExecutorUtils.printState;
import static com.example.praticejavaadv.util.MyLogger.log;
import static com.example.praticejavaadv.util.ThreadUtils.sleep;

@Slf4j
public class PoolSizeMainV2 {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        log("pool 생성");
        printState(es);

        for (int i = 1; i <= 6; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask(taskName));
            printState(es, taskName);
        }

        es.close();
        log("close() 완료");
    }
}
