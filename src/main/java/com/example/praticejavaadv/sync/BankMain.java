package com.example.praticejavaadv.sync;

import static com.example.praticejavaadv.util.MyLogger.log;
import static com.example.praticejavaadv.util.ThreadUtils.sleep;

public class BankMain {

    public static void main(String[] args) throws InterruptedException {
        // BankAccount account = new BankAccountV1(1000);
        BankAccount account = new BankAccountV2(1000);

        Thread thread1 = new Thread(new WithdrawTask(account, 800), "t1");
        Thread thread2 = new Thread(new WithdrawTask(account, 800), "t2");

        thread1.start();
        thread2.start();

        sleep(1000);
        log("t1 state : " + thread1.getState());
        log("t2 state : " + thread2.getState());

        thread1.join();
        thread2.join();

        log("최종 잔액 : " + account.getBalance());
    }
}
