package com.example.praticejavaadv.collection.simple.java;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class MapMain {

    public static void main(String[] args) {
        Map<Integer, String> map1 = new ConcurrentHashMap<>(); // 들어온 순서 보장 안 됨. HashMap 의 대안이다.
        map1.put(3, "data3");
        map1.put(2, "data2");
        map1.put(1, "data1");
        System.out.println("map1 = " + map1);

        Map<Integer, String> map2 = new ConcurrentSkipListMap<>(); // 정렬 지원함. TreeMap 의 대안이다.
        map2.put(2, "data2");
        map2.put(3, "data3");
        map2.put(1, "data1");
        System.out.println("map2 = " + map2);
    }
}
