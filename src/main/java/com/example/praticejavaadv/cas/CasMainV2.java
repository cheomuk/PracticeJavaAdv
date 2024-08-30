package com.example.praticejavaadv.cas;

import java.util.concurrent.atomic.AtomicInteger;

import static com.example.praticejavaadv.util.MyLogger.log;

public class CasMainV2 {

    /*
        락 없이 값을 증가시키는 기능을 구현한다.
     */

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value : " + atomicInteger.get());

        int resultValue1 = incrementAndGet(atomicInteger);
        System.out.println("resultValue1 : " + resultValue1);

        int resultValue2 = incrementAndGet(atomicInteger);
        System.out.println("resultValue2 : " + resultValue2);
    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;

        do {    // 만약 result 가 false 가 나왔다면 다시 실행. false 가 나온 이유는 다른 스레드가 중간에 값을 변경했기 때문!
            getValue = atomicInteger.get(); // 이때 가져온 값이
            log("getValue: " + getValue);
            result = atomicInteger.compareAndSet(getValue, getValue + 1);   // 여기까지 변하지 않았다면 +1을 진행한다!
        } while (!result);

        return getValue + 1;
    }
}
