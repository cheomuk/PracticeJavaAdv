package com.example.praticejavaadv.executor.reject;

import com.example.praticejavaadv.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectMainV3 {

    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(1, 1, 0,
                TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());

        executor.submit(new RunnableTask("task1"));
        executor.submit(new RunnableTask("task2")); // 작업을 요청한 스레드에 작업을 시킨다. (Main 스레드가 실행)
        executor.submit(new RunnableTask("task3")); // 마찬가지
        executor.submit(new RunnableTask("task4")); // task1 의 작업이 끝난 후이므로 스레드가 실행한다.

        executor.close();
    }
}
