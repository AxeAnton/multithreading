package ru.ifmo.lessons.lesson25;
// test
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Application {
    public static void main(String[] args) {
        // 1.изначально потоко безопасными коллекциями считались , Vector, HashTable, Stack, сейчас не используются
        // NB потока безапастная коллекция или мапа.
        // 2. из любой коллекции или мапы можно создать потокобезопасную так: Указываем ТД коллекции добовляем дженерик, обращаемся к классу колекции Collection и если нам нужен потоко безопасныйс список, то вызываем метод синхронайс лист. И передаем в метод синхронайт лист любую коллекуию (ArrayList).
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        HashSet<String> set = new HashSet<>(); //3. так же можно создать и множество потокобезопастное
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        Set<String> synchronizedSet = Collections.synchronizedSet(new HashSet<>(set));
        Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>()); //3. так же можно создать и потокобезопастную мапу.
        // 4. когда поток обращается к коллекции или мапе поток блакирует его полностью
        // NB класс java.util.councurrent -
        // 5. помимо методjd synchronizedList/Set/Map в jave есть пакет councurrent для многопоточности, там есть альтернатива с многопоточностью, synchronized блоков, waitNotify, код по расписани
         LinkedBlockingQueue<String> strings = new LinkedBlockingQueue<>(); // 17. Внутри этой очереди ArrayBlockingQueue массив, а внутри этой LinkedBlockingQueue связаный список, он вообще может не принимать в аргумент/конструктор - размер очереди, по умолчанию он равен максимальному размеру int (256 может хранить).
        strings = new LinkedBlockingQueue<>(30); // 15. флагов справедливости по умолчанию False, только задание количества

        ArrayBlockingQueue<Message> messages = new ArrayBlockingQueue<>(30);// 14. очереди на вход обязательно нужно указать количество элементов в очереди максимум 30, это значит 31 элемент не будет добавлен, а будет заблокирован.
        messages = new ArrayBlockingQueue<>(30, true); // 16. а если флагу справедливости задать значение то он будет true, гарантия того, что после разблокировки, первый который был заблокирован получит доступ к очереди, ресурсоемкая операция и если не нужно, его не используем.

        new Thread(new WriteThread(messages)).start(); //17. создаем потоки и передаем их планировщику
        new Thread(new WriteThread(messages)).start();
        new Thread(new WriteThread(messages)).start();
        new Thread(new ReadThread(messages)).start();

        // NB DelayQueue - ИСПОЛЬЗУЕТСЯ в РОСПИСАНИЯХ
        // 18. блокирующая очередь DelayQueue, особенность 1 - класс, экземпляры которого помещаются в очередь должен имплементировать Delayed интерфейс 2 - take - если данных нет, поток блакируется, или если данные нельзя извлечь, критерии оценки определяет разработчик (например, будущее время которое еще не пришло, когда придет тогда будет извлечены из очереди).

        DelayQueue<Task> tasks = new DelayQueue<>(); // 24. Создаем очередь с элентами task, обратить внимание, что у очереди не инфо по количеству объектов и нет флагов справедливости
        // 25. метод put вызывает метод compareto что бы разместить элементы в отсортированном порядке
        tasks.put(new Task(() -> { // 25. очередь хранит экземпляры класса task, значит в put должны передавать экземпляры task
            System.out.println("old task"); // 26. Конструктор (new Task(( принимает ДВА параметра, первый public  void run(){, а второй время - LocalDateTime.now().minusDays(1)
        }, LocalDateTime.now().minusDays(1)));
        // 27. интересный момент, данные программы ОБЫЧНО приходят из другой программы, например ежедневник в outlppk
        tasks.put(new Task(() -> {
            System.out.println("future task");
        }, LocalDateTime.now().plusMinutes(3)));
        // 28. данные программы это механизм добавления инфо.
        tasks.put(new Task(() -> {
            System.out.println("now task");
        }, LocalDateTime.now().plusSeconds(20)));

        // 29. а есть еще механизм извлечения.
        while (true) {
            try { // 31. метод take вызывает метод getDelay (определяет нужно ли вытащить объект из очереди) объекта, Task,и если метод вернет положительное число (нельзя), поток блокируется) в данном случае это main поток.
                Runnable runnable = tasks.take().getAction(); // 30. Берем очередь tasks, вызываем метод take()получаем задачу от класса Task через getAction()
                new Thread().start();
            } catch (InterruptedException e) {
                throw  new RuntimeException(e);
            }
        }
    }
}
