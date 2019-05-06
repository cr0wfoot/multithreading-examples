package com.example.multithreading.patterns.semaphore;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        new Worker(semaphore, "Increasing", true).start();
        new Worker(semaphore, "Reducing", false).start();
    }
}
