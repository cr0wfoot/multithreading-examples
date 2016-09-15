package lesson1.arraycalculator;

import java.util.Arrays;

class CalculatorThread extends Thread {
    
    int arr[],
        from,
        to;
    double result;

    public CalculatorThread(int[] arr, int from, int to) {
        this.arr = arr;
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        for(int i = from; i < to; i++){
            result += Math.exp(Math.sin(arr[i]));
        }
    }

    public double getResult() {
        return result;
    }

}

public class SimpleArrayCalculator {
    
    public static void main(String args[]) throws InterruptedException{
        int arr[] = new int[2000];
        Arrays.fill(arr, 1);
        CalculatorThread thread1 = new CalculatorThread(arr, 0, arr.length/2);
        CalculatorThread thread2 = new CalculatorThread(arr, arr.length/2, arr.length);
        
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(thread1.getResult() + thread2.getResult());
    }
    
}
