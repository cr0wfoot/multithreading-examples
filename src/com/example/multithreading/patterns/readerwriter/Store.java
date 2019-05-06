package com.example.multithreading.patterns.readerwriter;

class Store {

    private static String message = "Message number 0";

    static void changeMessage(String message) {
        Store.message = message;
    }

    static String getMessage() {
        return message;
    }
}
