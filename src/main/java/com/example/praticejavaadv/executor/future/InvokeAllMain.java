package com.example.praticejavaadv.executor.future;

import com.example.praticejavaadv.executor.CallableTask;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.example.praticejavaadv.util.MyLogger.log;

public class InvokeAllMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(10);

        CallableTask task1 = new CallableTask("task1", 1000);
        CallableTask task2 = new CallableTask("task2", 2000);
        CallableTask task3 = new CallableTask("task3", 3000);

        List<CallableTask> tasks = List.of(task1, task2, task3);

        List<Future<Integer>> futures = es.invokeAll(tasks);    // 안에 포함된 모든 작업이 끝나야 값이 리턴된다.
        for (Future<Integer> future : futures) {
            Integer value = future.get();
            log("invokeAll value = " + value);
        }

        Integer value = es.invokeAny(tasks);    // 진행 중인 task 중 가장 먼저 끝난 것 하나를 반환하고 끝낸다.
        log("invokeAny value = " + value);

        es.close();
    }
}
