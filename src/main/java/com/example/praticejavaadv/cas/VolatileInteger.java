package com.example.praticejavaadv.cas;

public class VolatileInteger  implements IncrementInteger {

    volatile private int value; // volatile 을 붙인다 해도 달라지는 것은 없다!

    @Override
    public void increment() {
        value++;    // 원자적인 연산이 아님!!
    }

    @Override
    public int get() {
        return value;
    }
}
