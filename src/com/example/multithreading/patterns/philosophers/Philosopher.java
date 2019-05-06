package com.example.multithreading.patterns.philosophers;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {

    private int number;
    private List<Semaphore> fork;

    Philosopher(int number, List<Semaphore> fork) {
        this.number = number;
        this.fork = fork;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(getName() + " is here");
                fork.get(number - 1).acquire();
                fork.get((number) % 5).acquire();
                System.out.println(getName() + " is eating...");
                fork.get(number - 1).release();
                fork.get((number) % 5).release();
                System.out.println(getName() + " is thinking...");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
