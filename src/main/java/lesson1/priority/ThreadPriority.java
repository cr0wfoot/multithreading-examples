package lesson1.priority;

public class ThreadPriority extends Thread {
    
    @Override
    public void run(){
        while(!isInterrupted()){
            System.out.println("111111");
            Thread.yield();
        }
    }
    
}
