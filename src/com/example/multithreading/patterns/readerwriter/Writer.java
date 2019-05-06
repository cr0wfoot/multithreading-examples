package com.example.multithreading.patterns.readerwriter;

import java.util.concurrent.Semaphore;

import static com.example.multithreading.patterns.readerwriter.Store.changeMessage;

public class Writer extends Thread {

    private Semaphore semaphore;
    private String message;

    Writer(Semaphore semaphore, String message) {
        this.semaphore = semaphore;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println("Write message: " + message);
            changeMessage(message);
            semaphore.release();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
