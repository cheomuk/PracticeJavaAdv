package com.example.praticejavaadv.collection.simple.list;

public class SyncProxyList implements SimpleList {

    /*
        프록시 패턴을 구현한 것으로 원본 코드를 수정하지 않고도 중간에서 구현체로
        synchronized 를 사용하여 임계 영역을 구축했다.
     */
    private SimpleList target;

    public SyncProxyList(SimpleList target) {
        this.target = target;
    }

    @Override
    public synchronized int size() {
        return target.size();
    }

    @Override
    public synchronized void add(Object e) {
        target.add(e);
    }

    @Override
    public synchronized Object get(int index) {
        return target.get(index);
    }
}
