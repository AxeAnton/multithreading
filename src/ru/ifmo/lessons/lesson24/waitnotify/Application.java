package ru.ifmo.lessons.lesson24.waitnotify;

public class Application {
    public static void main(String[] args) {
        Library storage = new Library(); // 1. олицетворяет ресурс


        new Thread(new PutThread(storage)).start(); // 2. с ним работают несколько потоков
        new Thread(new GetThread(storage)).start();
        new Thread(new PutThread(storage)).start();
        new Thread(new GetThread(storage)).start();
    }
}