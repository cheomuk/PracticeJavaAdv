package com.example.praticejavaadv.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static com.example.praticejavaadv.util.MyLogger.log;

public abstract class ExecutorUtils {

    public static void printState(ExecutorService executorService) {
        if (executorService instanceof ThreadPoolExecutor poolExecutor) {
            int pool = poolExecutor.getPoolSize();  // 스레드 풀에서 관리되고 있는 스레드 갯수
            int active = poolExecutor.getActiveCount(); // 실제 작업 중인 스레드 갯수
            int queuedTasks = poolExecutor.getQueue().size(); // 큐에 대기 중인 작업 갯수
            long completedTask = poolExecutor.getCompletedTaskCount();  // 완료된 작업 갯수
            log("[pool=" + pool + ", active=" + active + ", queuedTasks=" + queuedTasks +
                    ", completedTask=" + completedTask + "]");
        } else {
            log(executorService);
        }
    }

    public static void printState(ExecutorService executorService, String taskName) {
        if (executorService instanceof ThreadPoolExecutor poolExecutor) {
            int pool = poolExecutor.getPoolSize();  // 스레드 풀에서 관리되고 있는 스레드 갯수
            int active = poolExecutor.getActiveCount(); // 실제 작업 중인 스레드 갯수
            int queuedTasks = poolExecutor.getQueue().size(); // 큐에 대기 중인 작업 갯수
            long completedTask = poolExecutor.getCompletedTaskCount();  // 완료된 작업 갯수
            log(taskName + " -> [pool=" + pool + ", active=" + active + ", queuedTasks=" + queuedTasks +
                    ", completedTask=" + completedTask + "]");
        } else {
            log(taskName + " -> " + executorService);
        }
    }
}
