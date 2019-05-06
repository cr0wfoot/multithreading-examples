package patterns.philosophers;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Philosopher4 extends Thread {
    
    List<Semaphore> fork;
    
    public Philosopher4(List<Semaphore> fork) {
        this.fork = fork;
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                System.out.println(getName() + " is here");
                fork.get(4).acquire();
                fork.get(3).acquire();
                System.out.println(getName() + " is eating...");
                fork.get(4).release();
                fork.get(3).release();
                System.out.println(getName() + " is thinking...");
            } catch (Exception ex) {
                Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
