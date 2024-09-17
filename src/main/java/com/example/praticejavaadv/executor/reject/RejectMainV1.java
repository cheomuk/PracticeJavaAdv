package com.example.praticejavaadv.executor.reject;

import com.example.praticejavaadv.executor.RunnableTask;

import java.util.concurrent.*;

import static com.example.praticejavaadv.util.MyLogger.log;

public class RejectMainV1 {

    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(1, 1, 0,
                TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.AbortPolicy());

        executor.submit(new RunnableTask("task1"));
        try {
            executor.submit(new RunnableTask("task2"));
        } catch (RejectedExecutionException e) {
            log("요청 초과");
            // 포기, 다시 시도 등 다양한 고민을 하면 됨.
            log(e);
        }

        executor.close();
    }
}
