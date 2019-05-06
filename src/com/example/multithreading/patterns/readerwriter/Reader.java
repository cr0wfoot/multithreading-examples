package com.example.multithreading.patterns.readerwriter;

import java.util.concurrent.Semaphore;

public class Reader extends Thread {

    private static int readCounter = 0;

    private Semaphore semaphoreAccess;
    private Semaphore semaphoreRead;

    Reader(Semaphore semaphoreAccess, Semaphore semaphoreRead) {
        this.semaphoreAccess = semaphoreAccess;
        this.semaphoreRead = semaphoreRead;
    }

    @Override
    public void run() {
        try {
            semaphoreRead.acquire();
            readCounter++;
            if (readCounter == 1) {
                semaphoreAccess.acquire();
            }
            semaphoreRead.release();
            System.out.println("Read message: " + Store.getMessage());
            semaphoreRead.acquire();
            readCounter--;
            if (readCounter == 0) {
                semaphoreAccess.release();
            }
            semaphoreRead.release();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
