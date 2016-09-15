package lesson1.introduction;

public class Walk implements Runnable {

    @Override
    public void run() {
        for(int i = 0; i < 8; i++) {
            System.out.println("Walking");
            //Thread.yield();
            try {
                Thread.sleep(50);
            } catch(InterruptedException e) {
                System.err.print(e);
            }
        }
    }
    
}
