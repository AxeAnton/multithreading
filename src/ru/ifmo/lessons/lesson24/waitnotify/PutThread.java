package ru.ifmo.lessons.lesson24.waitnotify;

public class PutThread implements Runnable{ //3. ПОТОК
    private Library library;

    public PutThread(Library library){
        this.library = library;
    }

    @Override
    public void run() { // 4. переопределение RUN
        while (true) {
            System.out.println("put поток готовит данные для library");
            try {
                Thread.sleep((long)(Math.random() * 2000));
                library.putBook(new Library.Book()); // 5. добовление книги в библиотеук putBook
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}