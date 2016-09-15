package lesson1.interruption;

public class Main {
    
    public static void main(String[] args) throws InterruptedException {
        InterruptedThread thread = new InterruptedThread();
        thread.start();
        Thread.sleep(150);
        thread.interrupt();
    }
    
}
