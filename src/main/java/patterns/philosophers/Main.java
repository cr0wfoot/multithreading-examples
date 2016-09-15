package patterns.philosophers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {
    
    public static void main(String[] args) throws InterruptedException {
        
        List<Semaphore> fork = new ArrayList<>(); 
        fork.add(new Semaphore(1));
        fork.add(new Semaphore(1));
        fork.add(new Semaphore(1));
        fork.add(new Semaphore(1));
        fork.add(new Semaphore(1));
        
        new Philosopher(1, fork).start();
        new Philosopher(2, fork).start();
        new Philosopher(3, fork).start();
        new Philosopher4(fork).start();
        new Philosopher(5, fork).start(); 
        
    }
    
}
