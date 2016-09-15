package lesson3.queue;

//import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
       ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
       Consumer consumer = new Consumer(queue);
       //consumer.start();
       Scanner in = new Scanner( System.in );
       
       ExecutorService ex = Executors.newSingleThreadExecutor();
       //ex.execute(consumer);
       Future<Integer> res = ex.submit((Callable<Integer>)consumer);
       //Collections.synchronizedList(null);
       
       try {
            while(true) {
                Integer i = Integer.parseInt(in.nextLine());
                queue.put(i * 1000);
                System.out.println("StudentProducer = " + i*1000);
            }
       } finally {
            //consumer.interrupt();
            System.out.println(res.get());
            ex.shutdown();
       }
        
    }
    
}
