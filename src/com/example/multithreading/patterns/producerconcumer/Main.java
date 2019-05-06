package com.example.multithreading.patterns.producerconcumer;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Queue queue = new Queue();
        ProcessProducer producer1 = new ProcessProducer(queue);
        //ProcessProducer producer2 = new ProcessProducer(queue);

        CpuConsumer consumer1 = new CpuConsumer(queue);
        CpuConsumer consumer2 = new CpuConsumer(queue);
        //CpuConsumer consumer3 = new CpuConsumer(queue);
        //CpuConsumer consumer4 = new CpuConsumer(queue);

        producer1.start();
        //producer2.start();

        consumer1.start();
        consumer2.start();
        //consumer3.start();        
        //consumer4.start();

        sleep(15000);

        consumer1.interrupt();
        consumer2.interrupt();
        //consumer3.interrupt();
        //consumer4.interrupt();
        producer1.interrupt();
        //producer2.interrupt();
    }
}
