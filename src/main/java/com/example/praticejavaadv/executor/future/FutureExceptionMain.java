package com.example.praticejavaadv.executor.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

import static com.example.praticejavaadv.util.MyLogger.log;
import static com.example.praticejavaadv.util.ThreadUtils.sleep;

@Slf4j
public class FutureExceptionMain {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        log("작업 전달");
        Future<Integer> future = es.submit(new ExCallable());
        sleep(1000);    // 잠시 대기

        try {
            log("future.get() 호출 시도, future.state() : " + future.state());
            Integer result = future.get();
            log("result value : " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log("e = " + e);
            Throwable cause = e.getCause(); // 원본 예외(내부에서 터진 예외)
            log("cause : " + cause);
        }
    }

    static class ExCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            log("Callable 실행, 예외 발생");
            throw new IllegalStateException("ex!");
        }
    }
}
