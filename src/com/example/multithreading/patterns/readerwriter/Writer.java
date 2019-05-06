package patterns.readerwriter;

import java.util.concurrent.Semaphore;

public class Writer extends Thread {
    
    private Semaphore semaphore;
    private String message;
    
    public Writer(Semaphore semaphore, String message) {
        this.semaphore = semaphore;
        this.message = message;
    }
    
    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println("Write message: " + message);
            Store.changeMessage(message);
            semaphore.release();
        } catch(Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
}
