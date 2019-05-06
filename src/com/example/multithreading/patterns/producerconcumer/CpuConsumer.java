package patterns.producerconcumer;

public class CpuConsumer extends Thread {
    
    Queue queue;

    public CpuConsumer(Queue queue) {
        this.queue = queue;
    }
    
    @Override
    public void run() {
        while(!isInterrupted()) {
            Product item = queue.pull(getName());
            if(item != null) {
                try {
                    System.out.println(getName() + " took product number " + item.time);
                    Thread.sleep(item.time);
                } catch (InterruptedException ex) {
                   return;
                }
            } else {
                System.out.println(getName() + " got null");
                Thread.yield();
            }
        }
    }
    
}
