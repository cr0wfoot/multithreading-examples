package com.example.multithreading.committee;

public class Document {

    private int number;
    private Type type;

    public enum Type {
        MATHEMATICIAN, BIOLOGIST
    }

    Document(Type type, int number) {
        this.type = type;
        this.number = number;
    }

    public int getNum() {
        return number;
    }

    public Type getType() {
        return type;
    }
}
