package com.example.praticejavaadv.executor.reject;

import com.example.praticejavaadv.executor.RunnableTask;

import java.util.concurrent.*;

import static com.example.praticejavaadv.util.MyLogger.log;

public class RejectMainV2 {

    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(1, 1, 0,
                TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.DiscardPolicy());

        executor.submit(new RunnableTask("task1"));
        executor.submit(new RunnableTask("task2")); // 예외가 발생하면 그냥 버린다. (조용히 버리는 정책)
        executor.submit(new RunnableTask("task3")); // 마찬가지

        executor.close();
    }
}
