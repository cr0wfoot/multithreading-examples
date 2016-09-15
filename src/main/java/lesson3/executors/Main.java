package lesson3.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

            service.schedule(new Runnable(){
                @Override
                public void run(){
                    System.out.println("execute after 5 sec once");
                }
            }, 5, TimeUnit.SECONDS);

        
            service.scheduleAtFixedRate(new Runnable(){
                @Override
                public void run(){
                    System.out.println("fixed rate after 3 sec with 2 sec step");
                }
            }, 3, 2, TimeUnit.SECONDS);
            
            service.scheduleWithFixedDelay(new Runnable(){
                @Override
                public void run(){
                    System.out.println("fixed delay 1 sec");
                }
            }, 0, 1, TimeUnit.SECONDS);
        
    }
}
