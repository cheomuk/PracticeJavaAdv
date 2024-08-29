package com.example.praticejavaadv.cas;

public class SyncInteger implements IncrementInteger {

    private int value;

    @Override
    public synchronized void increment() {
        value++;    // 원자적인 연산이 아님!!
    }

    @Override
    public synchronized int get() {
        return value;
    }
}
