package lesson1.interruption;

public class InterruptedThread extends Thread {
    
    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            if(Thread.interrupted()) {
                System.out.println("Interruption");
                return;
            }
            System.out.println("Thread is running");
            try {
                Thread.sleep(51);
            } catch(InterruptedException e) {
                System.err.print(e);
                return;
            }
        }
    }
    
}
