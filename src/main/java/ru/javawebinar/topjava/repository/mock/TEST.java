package ru.javawebinar.topjava.repository.mock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TEST {
    public static void main(String[] args) {
        Map<Integer, String> repository = new ConcurrentHashMap<>();

        repository.put(1,"hi");

        System.out.println(repository.remove(1));

        System.out.println(repository.remove(2));
    }
}
