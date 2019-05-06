package patterns.philosophers;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Philosopher extends Thread {
    
    private int num;
    List<Semaphore> fork;
    
    public Philosopher(int num, List<Semaphore> fork) {
        this.num = num;
        this.fork = fork;
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                System.out.println(getName() + " is here");
                fork.get(num-1).acquire();
                fork.get((num)%5).acquire();
                System.out.println(getName() + " is eating...");
                fork.get(num-1).release();
                fork.get((num)%5).release();
                System.out.println(getName() + " is thinking...");
            } catch (Exception ex) {
                Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
