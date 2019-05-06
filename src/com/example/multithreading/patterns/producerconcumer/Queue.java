package com.example.multithreading.patterns.producerconcumer;

import java.util.LinkedList;
import java.util.List;

public class Queue {

    private static final int MAX_SIZE = 10;

    private List<Product> items = new LinkedList<>();

    synchronized void push(Product item) {
        if (items.size() >= MAX_SIZE) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("put product number " + item.time);
        items.add(item);
        notify();
    }

    synchronized Product pull(String name) {
        if (items.isEmpty()) {
            try {
                System.out.println(name + " waiting...");
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        //notify(); //for overloaded storage
        return items.isEmpty() ? null : items.remove(0);
    }
}
