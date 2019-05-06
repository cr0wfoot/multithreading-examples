package patterns.producerconcumer;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Queue {
    
    List<Product> items = new LinkedList<>();
    public static int MAX_SIZE = 10;
    
    synchronized public void push(Product item) {
        if(items.size() >= MAX_SIZE ) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Queue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("put product number " + item.time);
        items.add(item);
        notify();
    }
    
    synchronized public Product pull(String name) {
        if(items.isEmpty()) {
            try {
                System.out.println(name + " waiting...");
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Queue.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        //notify(); для переполнения хранилища
        return items.isEmpty() ? null : items.remove(0); 
    }
    
}
