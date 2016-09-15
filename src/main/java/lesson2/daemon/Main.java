package lesson2.daemon;

class Printer {
    
    synchronized public void print(int i) {
        System.out.println(i);
    }
    
}

class MyThread extends Thread {
    
    Printer printer;
    
    public MyThread(Printer printer) {
        this.printer = printer;
    }
    
    @Override
    public void run() {
        synchronized(System.out) {
            for(int i = 0; i < 10000; i += 2) {
                //System.out.println(i);
                synchronized(printer) {
                    printer.print(i);
                }
                Thread.yield();
                //try {
                //    Thread.sleep(100);
                //} catch (InterruptedException ex) {
                //    return ;
                //}
                if(this.isInterrupted()){
                    return;
                }
            }
        }
    }
    
}

public class Main {
    
    public static void main(String args[]) throws InterruptedException{
        
        final Printer printer = new Printer();
        Thread thread1 = new MyThread(printer);
        /*
        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
              synchronized(printer) {
                for(int i = 1; i < 10000; i+=2){
                    synchronized(System.out) {
                        System.out.println(i);
                    }
                    Thread.yield();
                    if(Thread.interrupted()) {
                        return;
                    }
                }
              }
            }
        });
        */
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread1.setDaemon(true);
        //thread2.setDaemon(true);
        thread1.start();
        //thread2.setPriority(Thread.MAX_PRIORITY);
        //thread2.start();
        thread1.join();
        //thread2.join();
        System.out.println("thread are finished!");
        thread1.interrupt();
         
    }
    
}
