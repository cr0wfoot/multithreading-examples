package com.example.multithreading.committee;

import com.example.multithreading.committee.faculties.BioFaculty;
import com.example.multithreading.committee.faculties.MathFaculty;
import com.example.multithreading.committee.faculties.MixFaculty;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore facultySemaphore = new Semaphore(1);
        Semaphore producerSemaphore = new Semaphore(2);
        Queue queue = new Queue(producerSemaphore);
        StudentProducer studentProducer = new StudentProducer(queue, producerSemaphore);
        MixFaculty mixFaculty = new MixFaculty(queue, facultySemaphore);
        MathFaculty mathFaculty = new MathFaculty(queue, facultySemaphore);
        BioFaculty bioFaculty = new BioFaculty(queue, facultySemaphore);

        studentProducer.start();
        mixFaculty.start();
        mathFaculty.start();
        bioFaculty.start();
    }
}
