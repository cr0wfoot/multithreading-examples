package com.example.multithreading.patterns.barber;

public class Barber extends Thread {

    private BarberShop shop;

    public Barber(BarberShop shop, String name) {
        super(name);
        this.shop = shop;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                shop.signalBarber();
                shop.waitClient();
                System.out.println("+++Barber working >>>>>");
                shop.signalService();
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
