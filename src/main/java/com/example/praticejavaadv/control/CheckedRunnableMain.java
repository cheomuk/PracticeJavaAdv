package com.example.praticejavaadv.control;

import static com.example.praticejavaadv.util.ThreadUtils.sleep;

public class CheckedRunnableMain {

    public static void main(String[] args) throws Exception {
        throw new Exception();
    }

    static class CheckedRunnable implements Runnable {

        @Override
        public void run() {
            sleep(1000);
        }
    }
}
