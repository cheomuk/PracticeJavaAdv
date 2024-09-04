package com.example.praticejavaadv.collection.simple.java;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListMain {

    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>();  // ArrayList 의 대안이다.
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println("list = " + list);
    }
}
