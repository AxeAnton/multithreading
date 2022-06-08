package ru.ifmo.lessons.lesson25.test1;

import ru.ifmo.lessons.lesson25.Message;
import ru.ifmo.lessons.lesson25.WriteThread;

import java.util.concurrent.ArrayBlockingQueue;

public class ApplicationCook {

    public static void main(String[] args) {
        ArrayBlockingQueue<Oder> queue1 = new ArrayBlockingQueue<>(10);
        ArrayBlockingQueue<Oder> queue2 = new ArrayBlockingQueue<>(10);
        ArrayBlockingQueue<Oder> queue3 = new ArrayBlockingQueue<>(10);

        new Thread(new Waiter(queue1, queue2)).start();
        new Thread(new Cook(queue2, queue3)).start();
        new Thread(new Castomer(queue1, queue3)).start();
    }


}
