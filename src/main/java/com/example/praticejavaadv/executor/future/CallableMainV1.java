package com.example.praticejavaadv.executor.future;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.*;

import static com.example.praticejavaadv.util.MyLogger.log;
import static com.example.praticejavaadv.util.ThreadUtils.sleep;

@Slf4j
public class CallableMainV1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        // ExecutorService es = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS,
        //                new LinkedBlockingQueue<>()); -> 이 코드를 위 코드로 간단히 사용할 수 있다!
        Future<Integer> future = es.submit(new MyCallable());
        Integer result = future.get();  // Callable 의 call 반환값을 가져올 수 있다.
        // Integer result = es.submit(new MyCallable()).get();  // 이렇게도 줄일 수 있다!
        log("result value = " + result);
        es.close();
    }

    static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() {
            log("Callable 시작");
            sleep(2000);
            int value = new Random().nextInt(10);
            log("create value = " + value);
            log("Callable 완료");
            return value;
        }
    }
}
