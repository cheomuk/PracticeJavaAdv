package com.example.praticejavaadv.collection.simple.java;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetMain {

    public static void main(String[] args) {
        Set<Integer> copySet = new CopyOnWriteArraySet<>(); // 들어온 순서 보장 안 됨.
        copySet.add(2);
        copySet.add(1);
        copySet.add(3);
        System.out.println("copySet = " + copySet);

        Set<Integer> skipListSet = new ConcurrentSkipListSet<>();  // 들어온 순서 보장됨. 정렬은 따로 해야 함.
        skipListSet.add(3);
        skipListSet.add(2);
        skipListSet.add(1);
        System.out.println("skipListSet = " + skipListSet);
    }
}
