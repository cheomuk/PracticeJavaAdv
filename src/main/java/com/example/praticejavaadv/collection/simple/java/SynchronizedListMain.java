package com.example.praticejavaadv.collection.simple.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedListMain {

    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        // 단순하게 사용할 수 있지만 무식하게 전부 동기화를 걸어버리니 성능 저하가 심한 것이 단점이다.
        // 이런 점을 보완하기 위해 동시성 컬렉션이란 것이 나왔다.
        list.add("data1");
        list.add("data2");
        list.add("data3");

        System.out.println(list.getClass());
        System.out.println("list = " + list);
    }
}
