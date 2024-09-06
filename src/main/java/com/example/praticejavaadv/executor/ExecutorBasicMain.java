package com.example.praticejavaadv.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.example.praticejavaadv.executor.ExecutorUtils.printState;
import static com.example.praticejavaadv.util.MyLogger.log;
import static com.example.praticejavaadv.util.ThreadUtils.sleep;

public class ExecutorBasicMain {

    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        /*
            corePoolSize : 스레드 풀에서 관리되는 기본 스레드 수
            maximumPoolSize : 스레드 풀에서 관리되는 최대 스레드 수
            keepAliveTime, TimeUnit unit : 기본 스레드 수를 초과해서 만들어진 스레드가 생존할 수 있는 대기 시간. 이 시간동안
                처리할 작업이 없다면 초과 스레드는 제거된다.
            BlockingQueue : 작업을 보관할 블로킹 큐
         */
        log("== 초기 상태 ==");
        printState(es);

        es.execute(new RunnableTask("taskA"));
        es.execute(new RunnableTask("taskB"));  // 여기까진 스레드가 만들어짐.
        es.execute(new RunnableTask("taskC"));  // 여기서부턴 만들어진 스레드를 재사용함.
        es.execute(new RunnableTask("taskD"));
        log("== 작업 수행 중 ==");
        printState(es);

        sleep(3000);
        log("== 작업 수행 완료 ==");
        printState(es);

        es.close();
        log("== shutdown 완료 ==");
        printState(es);
    }
}
