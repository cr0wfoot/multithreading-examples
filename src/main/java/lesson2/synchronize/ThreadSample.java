/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lesson2.synchronize;

/**
 *
 * @author sds
 */
class MyThisThread extends Thread{
    public void run(){
        try {
           synchronized(this){
            System.out.print("Hello ");
            
        
             this.wait();
             //Thread.sleep(200);
             //notifyAll();
            
            System.out.print("!");
        }
        } catch (InterruptedException ex) {                
        }
    }
}

public class ThreadSample {

    
    public static void main(String[] args) throws InterruptedException {
        MyThisThread thread = new MyThisThread();
        thread.start();
        Thread.sleep(100);
        synchronized(thread){
            System.out.print("world");
            
            thread.notify();
            
            System.out.print("?");
        }
    }
}
