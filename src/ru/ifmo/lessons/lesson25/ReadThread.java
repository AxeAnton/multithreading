package ru.ifmo.lessons.lesson25;

import java.util.concurrent.ArrayBlockingQueue;

public class ReadThread implements Runnable{

    private ArrayBlockingQueue<Message> messages;

    public ReadThread(ArrayBlockingQueue<Message> messages) {
        this.messages = messages;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted());{
            try {
                Message message = messages.take();
                // 12. удаляет и возвращает первый элемент из коробки/очереди, поток блокируется, если в очереди нет элементов, до тех пор, пока они там не появится.
                System.out.println("сообщение из очереди" + message);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt(); //13. то же самое что и в 11
            }
        }
    }
}
