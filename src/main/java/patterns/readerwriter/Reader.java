package patterns.readerwriter;

import java.util.concurrent.Semaphore;

public class Reader extends Thread {
    
    private Semaphore semaphoreAccess;
    private Semaphore semaphoreRead;
    
    public Reader(Semaphore semaphoreAccess, Semaphore semaphoreRead) {
        this.semaphoreAccess = semaphoreAccess;
        this.semaphoreRead = semaphoreRead;
    }
    
    @Override
    public void run() {
        try {
            semaphoreRead.acquire();
            Main.ReadCounter++;
            if(Main.ReadCounter == 1) {
                semaphoreAccess.acquire();
            }
            semaphoreRead.release();
            System.out.println("Read message: " + Store.getMessage());
            semaphoreRead.acquire();
            Main.ReadCounter--;
            if(Main.ReadCounter == 0) {
                semaphoreAccess.release();
            }
            semaphoreRead.release();
        } catch(Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
}
