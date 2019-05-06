package com.example.multithreading;

import java.util.Scanner;
import java.util.concurrent.*;

public class ArrayBlockingQueueExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        Consumer consumer = new Consumer(queue);
        //consumer.start();
        Scanner scanner = new Scanner(System.in);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //executorService.execute(consumer);
        Future<Integer> result = executorService.submit((Callable<Integer>) consumer);
        try {
            while (true) {
                Integer i = Integer.parseInt(scanner.nextLine());
                queue.put(i * 1000);
                System.out.println("StudentProducer value = " + i * 1000);
            }
        } finally {
            //consumer.interrupt();
            System.out.println(result.get());
            executorService.shutdown();
        }
    }

    private static class Consumer extends Thread implements Callable<Integer> {

        final BlockingQueue<Integer> queue;

        Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            int n = 5;
            while (--n > 0) {
                try {
                    Integer value = queue.take();
                    System.out.println("Consumer value = " + value);
                    sleep(value);
                } catch (InterruptedException ex) {
                    return;
                }
            }
        }

        @Override
        public Integer call() {
            run();
            return 0;
        }
    }
}
