package com.example.multithreading.patterns.semaphore;

import java.util.concurrent.Semaphore;

import static com.example.multithreading.patterns.semaphore.Cart.*;

public class Worker extends Thread {

    private Semaphore semaphore;
    private String name;
    private boolean increasing;

    Worker(Semaphore semaphore, String name, boolean increasing) {
        this.semaphore = semaphore;
        this.name = name;
        this.increasing = increasing;
    }

    @Override
    public void run() {
        System.out.println(name + " started working...");
        try {
            System.out.println(name + " waiting for cart...");
            semaphore.acquire();
            System.out.println(name + " got access to cart...");
            for (int i = 0; i < 10; i++) {
                if (!increasing) {
                    reduceWeight();
                } else {
                    increaseWeight();
                }
                System.out.println(name + " change weight to " + getWeight());
                sleep(10L);
            }
            semaphore.release();
            System.out.println(name + " finished working with cart...");
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
