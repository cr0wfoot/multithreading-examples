package lesson2.notify;

import java.util.Random;

class Saver {
    
    private int value;
    public boolean firstDone;
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
}

class ThreadGenerator extends Thread {
    
    Saver saver;
    
    public ThreadGenerator(Saver saver) {
        this.saver = saver;
    }
    
    @Override
    public void run() {
        Random r = new Random();
        for(int i = 0; i < 10; i++) {
            int side = r.nextInt(10);
            while(!saver.firstDone) {
                Thread.yield();
            }
            synchronized(saver) {
                System.out.println("Side = " + side);
                saver.setValue(side);
                saver.notify();
                try {
                    saver.wait();
                } catch(InterruptedException ex) {
                    return;
                }
            }
        }
    }
    
}

class ThreadCalculator extends Thread {
    
    Saver saver;
    
    public ThreadCalculator(Saver saver) {
        this.saver = saver;
    }
    
    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            synchronized(saver) {
                try {
                    saver.firstDone = true;
                    saver.wait();
                } catch(InterruptedException ex) {
                    return;
                }
                int side = saver.getValue();
                System.out.println("Square = " + side*side);
                saver.notify();
            }
        }
    }
    
}

public class Main {
    
    public static void main(String args[]) throws InterruptedException{
        System.out.println("TAK");
        Saver saver = new Saver();
        ThreadCalculator calculator = new ThreadCalculator(saver);
        ThreadGenerator generator = new ThreadGenerator(saver);
        generator.start();   
        calculator.start();    
        Thread.sleep(500); 
    }
 
}
