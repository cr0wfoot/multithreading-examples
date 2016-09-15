package lesson2.synchronize;

public class MyThread extends Thread {
    
    private ThreadWriter s;
    
    public  MyThread(String str, ThreadWriter s) {
        super(str);
        this.s = s;
    }
    
    @Override
    public void run() {
        for(int i = 0; i < 5; i++) {
            s.writing(getName(), i);
        }
    }
    
}
