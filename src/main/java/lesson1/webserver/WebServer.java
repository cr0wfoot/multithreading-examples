package lesson1.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebServer {
    
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(50);
        while(true){
            final Socket connection = socket.accept();
            new Thread(){
                @Override
                public void run(){
                    try {
                        new Handler().process(connection);
                    } catch (IOException ex) {
                        try {
                            connection.close();
                        } catch (IOException ex1) {
                            Logger.getLogger(WebServer.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                }
            }.start();
        }
    }
    
    static class Handler {
        
        public void process(Socket connection) throws IOException{
            connection.getOutputStream().write("Hello world".getBytes());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(WebServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection.close();
        }
        
    }
    
}
