package patterns.semaphore;

import java.util.concurrent.Semaphore;

public class Main {
    
    public static void main(String[] args) throws InterruptedException {
        
        Semaphore semaphore = new Semaphore(1);
        new Worker(semaphore, "Adder", true).start();
        new Worker(semaphore, "Reducer", false).start();
        
    }
    
}
