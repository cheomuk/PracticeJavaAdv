package com.example.praticejavaadv.control;

public class CheckedExceptionMain {

    public static void main(String[] args) throws Exception {
        throw new Exception();
    }

    static class CheckedRunnable implements Runnable {
        /*
            부모 메서드에 예외 처리가 선언되어 있지 않기 때문에
            오버라이딩한 자식 메서드 또한 체크 예외를 잡을 수가 없음!
            RuntimeException 같은 경우는 사용 가능함.
         */
        @Override
        public void run() {
            throw new RuntimeException();
        }
    }
}
