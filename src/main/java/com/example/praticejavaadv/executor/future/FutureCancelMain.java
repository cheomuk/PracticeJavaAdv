package com.example.praticejavaadv.executor.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

import static com.example.praticejavaadv.util.MyLogger.log;
import static com.example.praticejavaadv.util.ThreadUtils.sleep;

@Slf4j
public class FutureCancelMain {

    private static boolean mayInterruptIfRunning = true;
    // private static boolean mayInterruptIfRunning = false;

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<String> future = es.submit(new MyTask());
        log("Future.state() : " + future.state());

        // 일정 시간 후 취소 시도
        sleep(3000);

        // cancel 호출
        log("Future.cancel(" + mayInterruptIfRunning + ") 호출");
        boolean cancelResult = future.cancel(mayInterruptIfRunning);
        log("Future.cancel(" + mayInterruptIfRunning + ") result: " + cancelResult);

        // 결과 확인
        try {
            log("Future result: " + future.get());
        } catch (CancellationException e) {
            log("Future 는 이미 취소되었습니다.");
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            try {
                for (int i = 0; i < 10; i++) {
                    log("작업 중: " + i);
                    Thread.sleep(1000); // 1초동안 슬립
                }
            } catch (InterruptedException e) {
                log("인터럽트 발생!");
                return "Interrupted";
            }

            return "Completed";
        }
    }
}