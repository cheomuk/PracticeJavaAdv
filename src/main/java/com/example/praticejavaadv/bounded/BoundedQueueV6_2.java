package com.example.praticejavaadv.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static com.example.praticejavaadv.util.MyLogger.log;

public class BoundedQueueV6_2 implements BoundedQueue {

    private BlockingQueue<String> queue;

    public BoundedQueueV6_2(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    /*
        이 예제는 BlockingQueue 의 기능을 확인해보는 코드로
        아래 코드는 V1과 같은 결과를 보여줌.
     */

    @Override
    public void put(String data) {
        boolean result = queue.offer(data); // 데이더 삽입 성공시 true, 실패 시 false 반환
        log("저장 시도 결과 = " + result);
    }

    @Override
    public String take() {
        return queue.poll();    // 꺼낼 데이터가 없으면 null 반환
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
