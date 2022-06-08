package ru.ifmo.lessons.lesson24.waitnotify;

public class GetThread implements Runnable{ //6.  ПОТОК
    private Library library;

    public GetThread(Library library) {
        this.library = library;
    }

    @Override
    public void run() { //7. переопределение RUN
        while (true) {
            try {
                System.out.println("get получил книгу: " + library.getBook());  //8. получить или удалить книгу из библиотеки
                Thread.sleep((long)(Math.random() * 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}