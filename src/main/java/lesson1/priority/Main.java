package lesson1.priority;

public class Main {
    
    public static void main(String args[]) {
        
        final Thread thread = new ThreadPriority();
        
        thread.setPriority(1);
        thread.start();
        
        new Thread(){
            @Override
            public void run(){
                setPriority(10);
                for(int i = 0; i < 100; i++){
                    System.out.println("222");
                    //Thread.yield();
                }
                thread.interrupt();
            }
        }.start();
        
    }
    
}
