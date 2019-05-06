package com.example.multithreading.committee;

import java.util.concurrent.Semaphore;

import static com.example.multithreading.committee.Document.Type.*;
import static java.lang.Math.random;

public class StudentProducer extends Thread {

    private final static int TOTAL_BIO_DOCS = 200;
    private final static int TOTAL_MATH_DOCS = 250;
    private final static int NUMBER_OF_DOCS_TO_ADD = 50;

    private Queue queue;
    private int bioDocs = 0;
    private int mathDocs = 0;
    private Semaphore prod;

    StudentProducer(Queue queue, Semaphore prod) {
        this.queue = queue;
        this.prod = prod;
    }

    private Document getDocument() {
        if (bioDocs >= TOTAL_BIO_DOCS && mathDocs >= TOTAL_MATH_DOCS) {
            return null;
        } else if ((int) (random() * values().length) > 0 && mathDocs < TOTAL_MATH_DOCS) {
            System.out.println(">>> pushed math " + ++mathDocs);
            return new Document(MATHEMATICIAN, mathDocs);
        } else if (bioDocs < TOTAL_BIO_DOCS) {
            System.out.println(">>> pushed bio " + ++bioDocs);
            return new Document(BIOLOGIST, bioDocs);
        } else {
            System.out.println(">>> pushed math " + ++mathDocs);
            return new Document(MATHEMATICIAN, mathDocs);
        }
    }

    @Override
    public void run() {
        Document document;
        while (!isInterrupted()) {
            try {
                prod.acquire();
                int count = (int) (random() * NUMBER_OF_DOCS_TO_ADD + 1);
                System.out.println("+++ will push " + count + " docs");
                for (int i = 0; i < count; i++) {
                    if ((document = getDocument()) == null) {
                        interrupt();
                    } else {
                        queue.push(document);
                    }
                }
                if (!isInterrupted()) {
                    prod.acquire();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}