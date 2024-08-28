package com.example.praticejavaadv.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BoundedQueueV6_4 implements BoundedQueue {

    private BlockingQueue<String> queue;

    public BoundedQueueV6_4(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        queue.add(data);    // java.lang.IllegalStateException: Queue full 에러 발생. (버퍼가 가득 찼을 때 add 를 수행할 시)
    }

    @Override
    public String take() {
        return queue.remove();  // java.util.NoSuchElementException 에러 발생. (버퍼가 비었을 때 remove 를 수행할 시)
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
