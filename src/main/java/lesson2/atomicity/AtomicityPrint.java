package lesson2.atomicity;

class Incrementor extends Thread {
    @Override
    public void run(){
        for( int i = 0; i<1000000; i++){
         synchronized(AtomicityPrint.lock){
                AtomicityPrint.value++;
           }
        }
    }
}
public class AtomicityPrint {
    
    static volatile int value;
    static Object lock = new Object();
    
    public static void main( String args[]) throws InterruptedException{
     //   lock.notify();
        new Incrementor().start();
        while (value<1000000){
          synchronized( lock ){
            if(value%2==0){
                //System.out.print("value=");
                System.out.println("value="+value);
            }
          }
        }
    }
}
