package com.example.multithreading;

import static java.util.Arrays.fill;

public class ParallelArrayCalculatorExample {

    public static void main(String[] args) throws InterruptedException {
        final int arrayOfRandomNumbers[] = new int[100000];
        final int amountOfParallelExecutions = 10;
        fill(arrayOfRandomNumbers, 2);

        final Core[] threads = splitArrayOnThreads(arrayOfRandomNumbers, amountOfParallelExecutions);
        Thread[] wrappedThreads = wrapRunnableThreads(threads, amountOfParallelExecutions);
        startAllThreads(wrappedThreads);
        joinAllThreads(wrappedThreads);

        System.out.println(obtainTotalSum(threads));
    }

    private static Core[] splitArrayOnThreads(int[] arrayOfRandomNumbers, int amountOfParallelExecutions) {
        final Core[] arrayOfThreads = new Core[amountOfParallelExecutions];
        int step = arrayOfRandomNumbers.length / amountOfParallelExecutions;
        for (int i = 0; i < amountOfParallelExecutions; i++) {
            arrayOfThreads[i] = new Core(arrayOfRandomNumbers, step * i, step * i + step);
        }
        arrayOfThreads[arrayOfThreads.length - 1].setTo(arrayOfRandomNumbers.length);
        return arrayOfThreads;
    }

    private static Thread[] wrapRunnableThreads(Core[] arrayOfRunnableThreads, int amountOfParallelExecutions) {
        final Thread[] threads = new Thread[amountOfParallelExecutions];
        for (int i = 0; i < arrayOfRunnableThreads.length; i++) {
            threads[i] = new Thread(arrayOfRunnableThreads[i]);
        }
        return threads;
    }

    private static void startAllThreads(Thread[] threads) {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private static void joinAllThreads(Thread[] threads) throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
    }

    private static int obtainTotalSum(Core[] threadsWithResults) {
        int result = 0;
        for (Core coreThread : threadsWithResults) {
            result += coreThread.getSumOfElements();
        }
        return result;
    }

    private static class Core implements Runnable {

        private int[] array;
        private int from;
        private int to;
        private int sumOfElements;

        Core(int[] array, int from, int to) {
            this.array = array;
            this.from = from;
            this.to = to;
        }

        int getSumOfElements() {
            return sumOfElements;
        }

        void setTo(int to) {
            this.to = to;
        }

        @Override
        public void run() {
            int sum = 0;
            for (int i = from; i < to; i++) {
                sum += array[i];
            }
            sumOfElements = sum;
        }
    }
}
