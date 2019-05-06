package com.example.multithreading.patterns.philosophers;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Philosopher4 extends Thread {

    private List<Semaphore> fork;

    Philosopher4(List<Semaphore> fork) {
        this.fork = fork;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(getName() + " is here");
                fork.get(4).acquire();
                fork.get(3).acquire();
                System.out.println(getName() + " is eating...");
                fork.get(4).release();
                fork.get(3).release();
                System.out.println(getName() + " is thinking...");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
