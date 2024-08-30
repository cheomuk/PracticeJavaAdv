package com.example.praticejavaadv.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CasMainV1 {

    /*
        딱 봐도 CAS 연산은 2가지 계산이 들어감으로 원자적인 연산이 아닐 것처럼 보이지만, CPU 에서 이를 원자적으로 처리해준다.
        즉, 하드웨어가 지원하는 기능이기 때문에 이 연산이 실행 중일 때 다른 스레드가 개입할 수 없다.
        CPU 가 메모리에서 값을 확인할 때 접근을 통제하고 변경 연산을 진행한다.
     */

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value : " + atomicInteger.get());

        boolean result1 = atomicInteger.compareAndSet(0, 1);    // 값을 비교해보고 값이 맞으면 두 번째 파라미터 값으로 변경
        System.out.println("result1: " + result1 + ", value: " + atomicInteger.get());

        boolean result2 = atomicInteger.compareAndSet(0, 1);
        System.out.println("result2: " + result2 + ", value: " + atomicInteger.get());
    }
}
