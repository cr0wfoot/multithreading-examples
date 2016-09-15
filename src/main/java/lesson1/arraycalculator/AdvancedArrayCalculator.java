package lesson1.arraycalculator;

import java.util.Arrays;


class Core implements Runnable {

    private int arr[],
            from,
            to;
    private static int result;

    public Core(int arr[], int from, int to) {
        this.arr = arr;
        this.from = from;
        this.to = to;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int[] getArray() {
        return arr;
    }

    public void setArray(int[] arr) {
        this.arr = arr;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int i = from; i < to; i++) {
            sum += arr[i];
        }
        result = sum;
    }

}

public class AdvancedArrayCalculator {
    
    private static final int ARR[] = new int[100000];
    private static final int CORES = 10;
    
    public static void main(String[] args) throws InterruptedException {

        Arrays.fill(ARR, 2);
        Core[] threadsArray = new Core[CORES];
        Thread[] threads = new Thread[CORES];

        int step = ARR.length / CORES;

        for (int i = 0; i < CORES; i++) {
            threadsArray[i] = new Core(ARR, step * i, step * i + step);
        }

        threadsArray[threadsArray.length - 1].setTo(ARR.length);

        for (int i = 0; i < threadsArray.length; i++) {
            threads[i] = new Thread(threadsArray[i]);
        }
        for (int i = 0; i < threadsArray.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threadsArray.length; i++) {
            threads[i].join();
        }

        int sum = 0;

        for (Core thread : threadsArray) {
            sum += thread.getResult();
        }

        System.out.println(sum);
    }
    
}
