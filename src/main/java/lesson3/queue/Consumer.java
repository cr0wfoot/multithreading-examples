package lesson3.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class Consumer extends Thread implements Callable<Integer> {
    
    BlockingQueue<Integer>  queue;
    
    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    
    @Override
    public void run(){
        int n = 5;
        while(--n > 0) {
            try {
                Integer value = queue.take();
                System.out.println("Consumer = " + value);
                Thread.sleep(value);
            } catch (InterruptedException ex) {
                return ;
            }
        }
    }

    @Override
    public Integer call() {
        run();
        return 0;
    }
    
}
