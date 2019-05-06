package com.example.multithreading.patterns.semaphore;

class Cart {

    private static int weight = 0;

    static void increaseWeight() {
        weight--;
    }

    static void reduceWeight() {
        weight++;
    }

    static int getWeight() {
        return weight;
    }
}
