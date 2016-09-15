package lesson2.synchronize;

import java.io.FileWriter;
import java.io.IOException;

public class ThreadWriter {
    
    private FileWriter fileWriter;
    
    public ThreadWriter(String file) throws IOException {
        fileWriter = new FileWriter(file, true);
    }
    
    public void close() {
        try {
            fileWriter.close();
        } catch(IOException e) { 
            e.printStackTrace();
        }
    }
    
    public synchronized void writing(String str, int i) {
        try {
            System.out.print(str + i);
            fileWriter.append(str + i);
            Thread.sleep((long)(Math.random() * 50));
            System.out.print("->" + i + " ");
            fileWriter.append("->" + i + " ");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
