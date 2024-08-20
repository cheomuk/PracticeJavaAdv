package com.example.praticejavaadv.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.example.praticejavaadv.util.MyLogger.log;
import static com.example.praticejavaadv.util.ThreadUtils.sleep;

public class BankAccountV5 implements BankAccount {

    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV5(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작" + getClass().getSimpleName());

        if (!lock.tryLock()) {
            log("[진입 실패] 이미 처리 중인 작업이 있습니다.");
            return false;
        }

        try {
            log("[검증 시작]");
            if (balance < amount) {
                log("검증 실패 출금액 : " + amount + ", 잔액 : " + balance);
                return false;
            }

            // 잔고가 출금액보다 많으면, 진행
            log("[검증 성공] 출금액 : " + amount + ", 잔액 : " + balance);
            sleep(1000);    // 출금에 걸리는 시간
            balance -= amount;

            log("[출금 완료] 출금액 : " + amount + ", 잔액 : " + balance);
        } finally { // unlock 은 lock 을 반환하기 위해 무조건 finally 에서 실행해야 한다.
            lock.unlock();  // ReentrantLock 을 이용하여 Lock 해제
        }

        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
