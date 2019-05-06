package com.example.multithreading.committee.faculties;

import com.example.multithreading.committee.Document;
import com.example.multithreading.committee.Queue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

import static java.lang.Math.random;

public class MixFaculty extends Thread {

    private Semaphore semaphore;
    private Queue queue;
    private List<Document> docs = new LinkedList<>();

    public MixFaculty(Queue queue, Semaphore s) {
        this.queue = queue;
        this.semaphore = s;
    }

    @Override
    public void run() {
        try {
            sleep(300);
            while (!isInterrupted()) {
                semaphore.acquire();
                System.out.println("...mix started...");
                Document document;
                int count = (int) (random() * 5 + 1);
                for (int i = 0; i < count; i++) {
                    if ((document = queue.pop()) == null) {
                        semaphore.release();
                        interrupt();
                        break;
                    }
                    docs.add(document);
                    System.out.println("<-< mix took document " + document.getType() + " " + document.getNum());
                }
                if (!isInterrupted()) {
                    semaphore.release();
                    sleep(300);
                }
            }
            System.out.println("Mix finished and has " + docs.size() + " docs");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
