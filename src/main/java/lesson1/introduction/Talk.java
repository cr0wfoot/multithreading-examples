package lesson1.introduction;

public class Talk extends Thread {
    
    volatile private int iterations;
    
    public int getIterations() {
        return iterations;
    }
    
    public void setIterations(int iterations) {
        this.iterations = iterations;
    }
    
    public Talk(int iterations) {
        this.iterations = iterations;
    }
    
    @Override
    public void run() {
        for(int i = 0; i < iterations; i++, iterations--) {
            System.out.println("Extended Talking with name - " + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {
                System.err.print(e);
            }
        }
    }
    
}
