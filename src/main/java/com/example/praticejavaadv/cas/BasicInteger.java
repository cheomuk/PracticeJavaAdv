package com.example.praticejavaadv.cas;

public class BasicInteger implements IncrementInteger {

    private int value;

    @Override
    public void increment() {
        value++;    // 원자적인 연산이 아님!!
    }

    @Override
    public int get() {
        return value;
    }
}
