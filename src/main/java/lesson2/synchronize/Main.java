package lesson2.synchronize;

import java.io.IOException;

public class Main {
    
    public static void main(String[] args) throws InterruptedException {
        try {
            ThreadWriter s  = new ThreadWriter("resources/synchronize.txt");
            MyThread tread1 = new MyThread("First", s);
            MyThread tread2 = new MyThread("Second", s);
            tread1.start();
            tread2.start();
            tread1.join();
            tread2.join();
            s.close();
        } catch(IOException e) {
            System.err.print("ошибка файла");
            e.printStackTrace();
        } catch(InterruptedException e) {
            System.err.print("ошибка потока");
            e.printStackTrace();
        }
    }
    
}
