package com.example.praticejavaadv.cas;

public class IncrementPerformanceMain {

    public static final long COUNT = 100_000_000;

    public static void main(String[] args) {
        test(new BasicInteger());   // 가장 빠르다. CPU 캐시를 적극 사용한다. 단일 스레드가 사용하는 경우 효과적이다.
        test(new VolatileInteger());    // volatile 을 사용해서 CPU 캐시가 아닌 메인 메모리를 사용함. 안전한 임계 영역이 없음. 잘못된 코드다.
        test(new SyncInteger());    // 안전한 임계 영역이 있기 때문에 안전하게 사용 가능하지만 느리다.
        test(new MyAtomicInteger());    // Synchronized 나 Lock 을 사용하는 경우보다 1.5 ~ 2배 정도 빠르다.
        // incrementAndGet() 메서드는 락을 사용하지 않고 원자적 연산을 만들어 내기 때문에 빠른 것이다!
    }

    private static void test(IncrementInteger incrementInteger) {
        long startMs = System.currentTimeMillis();

        for (int i = 0; i < COUNT; i++) {
            incrementInteger.increment();
        }

        long endMs = System.currentTimeMillis();
        System.out.println(incrementInteger.getClass().getSimpleName() + ": ms= " + (endMs - startMs));
    }
}
