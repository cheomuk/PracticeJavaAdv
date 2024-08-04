package com.example.praticejavaadv.start;

public class BadThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main start()");

        HelloThread thread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": start() 호출 전");
        thread.run(); // run() 직접 실행 -> 별도의 스레드가 실행되는 것이 아닌 main 스택에 쌓여서 실행된다.
                    // -> Thread-0 같은 별도의 스레드 생성 x -> 그렇기 때문에 사용하지 않음!
        System.out.println(Thread.currentThread().getName() + ": start() 호출 후");

        System.out.println(Thread.currentThread().getName() + ": main end()");
    }
}
