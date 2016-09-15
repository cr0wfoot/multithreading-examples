package lesson2.atomicity;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    
    volatile static int i;
    static ReentrantLock lock = new ReentrantLock();
    
    public static class Atomicity extends Thread {
        
        boolean b;
        
        @Override
        public void run() {
            while(true) {
                //lock.lock();
                //try {
                //synchronized(Atomicity.class) {
                    if (b = !b) i++;
                    else i--;
                //}
                //} finally {
                //    lock.unlock();
                //}
            }
        }
        
    }
    
    public static void main(String[] args) throws InterruptedException {
        new Atomicity().start();
        new Atomicity().start();
        new Atomicity().start();
        
        while(true) {
            System.out.println(i);
            Thread.sleep(250);
        }
    }
    
}
