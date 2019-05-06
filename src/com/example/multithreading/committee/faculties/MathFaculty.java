package com.example.multithreading.committee.faculties;

import com.example.multithreading.committee.Document;
import com.example.multithreading.committee.Queue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

import static com.example.multithreading.committee.Document.Type.BIOLOGIST;

public class MathFaculty extends Thread {

    private Semaphore semaphore;
    private Queue queue;
    private List<Document> docs = new LinkedList<>();

    public MathFaculty(Queue queue, Semaphore s) {
        this.queue = queue;
        this.semaphore = s;
    }

    @Override
    public void run() {
        try {
            sleep(300);
            while (!isInterrupted()) {
                semaphore.acquire();
                Document document;
                System.out.println("...math started...");
                while (true) {
                    if ((document = queue.pop()) == null) {
                        semaphore.release();
                        interrupt();
                        break;
                    } else if (document.getType() == BIOLOGIST)
                        break;
                    docs.add(document);
                    System.out.println("<-< math took document " + document.getType() + " " + document.getNum());
                }
                if (!isInterrupted()) {
                    System.out.println(">-> math returned document " + document.getType() + " " + document.getNum());
                    queue.push(document);
                    semaphore.release();
                    sleep(300);
                }
            }
            System.out.println("Math finished and has " + docs.size() + " docs");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
