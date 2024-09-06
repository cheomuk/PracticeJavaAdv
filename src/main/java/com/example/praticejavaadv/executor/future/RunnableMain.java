package com.example.praticejavaadv.executor.future;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

import static com.example.praticejavaadv.util.MyLogger.log;
import static com.example.praticejavaadv.util.ThreadUtils.sleep;

@Slf4j
public class RunnableMain {

    /*
        Runnable 은 반환 타입이 void 이다. 따라서 값을 반환할 수 없다.
        예외가 선언되어 있지 않다. 따라서 해당 인터페이스를 구현하는 모든 메서드는 체크 예외를 던질 수 없다.
            - 자식은 부모의 예외 범위를 넘어설 수 없으므로 모든 구현체들은 예외를 던질 수 없다.
            - 런타임 예외는 제외다.

        Runnable 을 사용하면 무작위 값 하나 받아오는데도 이리 복잡하다.
        작업 스레드는 간단히 값을 return 을 통해 반환하고, 요청 스레드는 그 반환 값을 바로 받을 수 있다면
        코드가 훨씬 더 간결할 것이다.
        이 문제를 해결하기 위해 Executor 프레임워크는 Callable 과 Future 라는 인터페이스를 도입했다.
     */

    public static void main(String[] args) throws InterruptedException {
        MyRunnable task = new MyRunnable();
        Thread thread = new Thread(task);
        thread.start();
        thread.join();

        int result = task.value;
        log("result value = " + result);
    }

    static class MyRunnable implements Runnable {

        int value;

        @Override
        public void run() {
            log("Runnable 시작");
            sleep(2000);
            value = new Random().nextInt(10);
            log("create value = " + value);
            log("Runnable 완료");
        }
    }
}
