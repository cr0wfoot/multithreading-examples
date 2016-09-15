package lesson1.introduction;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        
        Talk talk = new Talk(8);
        Thread walk = new Thread(new Walk());
        
        final int count[] = {5};
        
        Thread temp = new Thread(){
            @Override
            public void run(){
                for(int i=0; i< count[0]; i++){
                    System.out.println("Hello from thread");
                    try{
                        Thread.sleep(100);
                    }catch(InterruptedException ex){
                        return;
                    }
                }
            }
        };
        count[0]=10;
        
        temp.start();
        temp.join();
        
        talk.start();
        talk.setIterations(14);
        //talk.run(); - сожрет часть итераций на себя
        walk.start();
        talk.join();
        walk.join();
        
        System.out.println(talk.getIterations());
        System.out.println("Hello from main thread - " + Thread.currentThread().getName());
    }
}
