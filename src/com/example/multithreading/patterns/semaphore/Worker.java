package patterns.semaphore;

import java.util.concurrent.Semaphore;

public class Worker extends Thread {
    
    private Semaphore sema;
    private String name;
    private boolean isAdder;
    
    public Worker(Semaphore sema, String name, boolean isAdder) {
        this.sema = sema;
        this.name = name;
        this.isAdder = isAdder;
    }
    
    @Override
    public void run() {
        System.out.println(name + " started working...");
        try {
            System.out.println(name + " waiting for cart...");
            sema.acquire();
            System.out.println(name + " got access to cart...");
            for(int i = 0; i < 10; i++) {
                if(!isAdder) Cart.reduceWeight();
                else Cart.addWeight();
                System.out.println(name + " change weight to " + Cart.getWeight());
                Thread.sleep(10L);
            }
            sema.release();
            System.out.println(name + " finished working with cart...");
        } catch(Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
}
