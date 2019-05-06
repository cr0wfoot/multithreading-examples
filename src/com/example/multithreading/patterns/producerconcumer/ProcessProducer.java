package com.example.multithreading.patterns.producerconcumer;

public class ProcessProducer extends Thread {

    private Queue queue;
    private int i = 0;

    ProcessProducer(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            i++;
            queue.push(new Product(i));
            try {
                sleep(700);
            } catch (InterruptedException ex) {
                return;
            }
        }
    }
}
