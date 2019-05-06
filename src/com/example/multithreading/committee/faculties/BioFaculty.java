package com.example.multithreading.committee.faculties;

import com.example.multithreading.committee.Document;
import com.example.multithreading.committee.Queue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

import static com.example.multithreading.committee.Document.Type.MATHEMATICIAN;

public class BioFaculty extends Thread {

    private Semaphore semaphore;
    private Queue queue;
    private List<Document> docs = new LinkedList<>();

    public BioFaculty(Queue queue, Semaphore semaphore) {
        this.queue = queue;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            sleep(300);
            while (!isInterrupted()) {
                semaphore.acquire();
                Document document;
                System.out.println("...bio started...");
                while (true) {
                    if ((document = queue.pop()) == null) {
                        semaphore.release();
                        interrupt();
                        break;
                    } else if (document.getType() == MATHEMATICIAN)
                        break;
                    docs.add(document);
                    System.out.println("<-< bio took document " + document.getType() + " " + document.getNum());
                }
                if (!isInterrupted()) {
                    System.out.println(">-> bio returned document " + document.getType() + " " + document.getNum());
                    queue.push(document);
                    semaphore.release();
                    sleep(300);
                }
            }
            System.out.println("Bio finished and has " + docs.size() + " docs");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
