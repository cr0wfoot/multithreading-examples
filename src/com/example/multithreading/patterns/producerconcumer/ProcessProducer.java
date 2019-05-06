package patterns.producerconcumer;

public class ProcessProducer extends Thread {
    
    Queue queue;
    int i = 0;
    
    public ProcessProducer(Queue queue) {
        this.queue = queue;
    }
    
    @Override
    public void run() {
        while(!isInterrupted()) {
            i++;
            queue.push(new Product(i));
            try {
                Thread.sleep(700);
            } catch (InterruptedException ex) {
                return;
            }
        }
    }
    
}
