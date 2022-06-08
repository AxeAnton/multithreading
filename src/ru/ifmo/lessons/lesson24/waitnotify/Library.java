package ru.ifmo.lessons.lesson24.waitnotify;

import com.sun.source.tree.WhileLoopTree;

import java.util.ArrayList;

// 13. У класса Object: есть методы:
// 14. NB wait() приостанавлевает работу потока до тех пор пока не будет разбужен вывзовом метода notify из другого потока.
// 15. NB wait() или мили сек
// 16. МОГУТ ПРОСНУТЬСЯ САМИ ПО СЕБЕ

// 17. NB notify ()  - возобнавляет работу потока который был преостановлем вызовом метода wait().
// 18. НЕЛЬЗЯ указать какой именно поток должен возобновить работу.

// 19. NB notifyAll () - возобновляет работу всех потоков которые были преостановлены вызовом метода wait()
// 20. можно вызвать только synchronized методе или блоке.

public class Library {
    private ArrayList<Book> books = new ArrayList<>(6);

   // 21. NB т.к и putBook и getBook вызываются из потока и МЕНЯЮТ состояние объекта ОБЩЕГО ресурса (Library), необходимо использовать synchronized.
    // 9. метод вызывается потоком,
    public synchronized void putBook(Book book) throws InterruptedException { //11. добавить не более шести книг
        // 22. if (books.size) > 5) wait(); т.к поток может проснуться сам то необходимо добавить его в whill
        while (books.size() > 5) {
            wait();
        }

        books.add(book);
        System.out.println("Книга добавлена, всего книг: " + books.size());
        notify(); // будит любой случайный поток
    }

    // 10. метод вызывается потоком,
    // 9. когда метод вызывается объект блокируется и ..........
    public synchronized Book getBook() throws InterruptedException { // 12. нельзя получить книгу из пустой колекции
        while (books.size() == 0) {
            wait();
        }
        Book book = books.remove(0);
        System.out.println("Удалена книга, всего книг: " + books.size());
        notify(); //23. что бы разбудить wait.
        return book;
    }

    static class Book{}
}