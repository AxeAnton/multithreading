package ru.ifmo.lessons.lesson26;


import ru.ifmo.lessons.lesson25.Message;
import java.util.concurrent.Callable;


// 46. Создаем класс, где КАЖДЫЙ (отдельный) поток будет или создать или принимает сообщение. Мы знаем два варианта либо класс должен наследоваться (extend) от Thread (+ переопределить метод run) или он должен имплементировать метод Runnable и переопределить интерфейс run.
public class MessageGenerator implements Callable<Message> { // 47. но есть еще один вариан, имплементировать метод Callable<Message>, где Message это инструкции для потока.
// 51. Экземпляр данного класса используется с пулами и нельзя передать в конструктор Thread

    @Override // 48. инструкции для потока которые должны быть выполнены
    public Message call() throws Exception { //49. ТД который возвращает поток <Message>
        Thread.sleep((long) (Math.random()*3000));
        String[] strings = {"сообщение 1", "сообщение 2", "сообщение 3"};
        String text = strings [(int) (Math.random() * strings.length)];
        Message message = new Message(text);
        System.out.println("Сообщение создано/ получино"  + message);
        return null; // 50. вернет результат своей работы
    }
}
