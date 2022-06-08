package ru.ifmo.lessons.lesson25;

import java.util.concurrent.ArrayBlockingQueue;

public class WriteThread implements Runnable{ // 7. клас при эмплементации данного интерфейса потоком не становится у него просто появляется метод run, зато потом в конструктор класса WeiteThread, мы можем передать ссылку на объект нашего класса.
    private ArrayBlockingQueue<Message> messages; // 6. Блокирующая очередь в которой будут хранится экземпляры класса (App)

    public WriteThread(ArrayBlockingQueue<Message> messages) {
        this.messages = messages;
    }


    @Override
    public  void run(){
        String[] strings = {"сообщение 1", "сообщение 2", "сообщение 3"};

        while (!Thread.currentThread().isInterrupted()) { // 8. Напоминание, что у каждого потока есть свойство Interrup изначально он False, он есть метод который может перевести его в True
            try {
                Thread.sleep(5000);
                String text = strings [(int) (Math.random()*strings.length)]; // 9. Экземпляр типа мессеж, и передаем ему рандомное сообщение!
                Message message = new Message (text);
                //10. метод put объект добовляется в конец очереди, если очередь переполнена поток блокируется, до тех пор пока место в очереди не освободится
                messages.put(message);
                System.out.println("данные добавлены в очередь" + message);
            } catch (InterruptedException e)   {
                e.printStackTrace();
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt(); // 11. обращение к текущему потоку currentThread() через метод interrapt во время ожидания put, приведет к выбрасыванию InterruptedExcepton и он поменяется на True и все это выражение (!Thread.currentThread().isInterrupted()) станет False и весь метод прирывания сломается, поэтому его еще раз необходимо перевести в True вызвав метод interrupt.
            }
        }
    }
}
