package patterns.readerwriter;

import java.util.concurrent.Semaphore;

public class Main {
    
    public static int ReadCounter = 0;
    
    public static void main(String[] args) throws InterruptedException {
        
        Semaphore ReadSemaphore = new Semaphore(1);
        Semaphore Access = new Semaphore(1);
        
        new Writer(Access, "message 1").start();
        new Writer(Access, "message 2").start();     
        new Writer(Access, "message 3").start();
        
        new Reader(Access, ReadSemaphore).start();  
        new Reader(Access, ReadSemaphore).start();  
        new Reader(Access, ReadSemaphore).start();  
        new Reader(Access, ReadSemaphore).start();  
        new Reader(Access, ReadSemaphore).start();  
        new Reader(Access, ReadSemaphore).start();
        new Reader(Access, ReadSemaphore).start();  
        
    }
}
