package com.example.multithreading;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class CustomWebServerExample {

    public static void main(String[] args) throws IOException {
        final ServerSocket socket = new ServerSocket(50);
        while (true) {
            final Socket connection = socket.accept();
            new Thread(() -> {
                try {
                    new Handler().process(connection);
                } catch (IOException e) {
                    try {
                        connection.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static class Handler {

        void process(Socket connection) throws IOException {
            //Put debug mark here to see execution
            connection.getOutputStream().write("Hello world".getBytes());
            try {
                sleep(10000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            connection.close();
        }
    }
}
