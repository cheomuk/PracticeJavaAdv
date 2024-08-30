package com.example.praticejavaadv.cas.increment;

import com.example.praticejavaadv.cas.increment.IncrementInteger;

import java.util.concurrent.atomic.AtomicInteger;

public class MyAtomicInteger implements IncrementInteger {

    AtomicInteger atomicInteger = new AtomicInteger(0); // 임계 영역이 알아서 구현되어 있다.

    @Override
    public void increment() {
        atomicInteger.incrementAndGet();
    }

    @Override
    public int get() {
        return atomicInteger.get();
    }
}
