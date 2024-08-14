package com.example.praticejavaadv.sync;

import static com.example.praticejavaadv.util.MyLogger.log;
import static com.example.praticejavaadv.util.ThreadUtils.sleep;

public class BankAccountV1 implements BankAccount {

    private int balance;

    public BankAccountV1(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작" + getClass().getSimpleName());
        // 잔고가 출금액보다 적으면, 진행하면 안 됨
        // 검증 시작
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
        log("거래 종료");

        return true;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
