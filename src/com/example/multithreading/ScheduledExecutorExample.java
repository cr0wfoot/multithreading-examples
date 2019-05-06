package com.example.multithreading;

import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ScheduledExecutorExample {

    public static void main(String[] args) {
        final ScheduledExecutorService scheduledExecutorService = newSingleThreadScheduledExecutor();

        scheduledExecutorService.schedule(() -> System.out.println("execute after 5 sec once"), 5, SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("fixed rate after 3 sec with 2 sec step"), 3, 2, SECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(() -> System.out.println("fixed delay 1 sec"), 0, 1, SECONDS);
    }
}
