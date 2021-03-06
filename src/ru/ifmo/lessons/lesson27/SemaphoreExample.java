package ru.ifmo.lessons.lesson27;

import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class SemaphoreExample { // 8. Один из вариантов блокировки ресурса - пока поток занят
    private volatile boolean prop; // 20. При наличии модификатора volatile (стандартное средство работы с многопоточностью) - потоки не кэшируют свойство, а обращаются к общей памяти => 21.
    // 21. Пример: каждый поток получил ссылку на переменную и каждый поток сохранил значение данной переменной false. Получается, что каждый поток считает, что значение свойств false, допустим если один из потоков поменял свойство переменной на true, но другие потоки не знают об этом, что свойство поменялось на true и они будут считать, что свойство prop = false, следовательно, такая ситуация будет приводить к ошибкам. Если мы добавим этот модификатор volatile, то потоки НЕ будут КЕШИРОВАТЬ свойства, а все время будут обращаться к свойствам переменной.
    /*
    * поток: 1 prop - false
    *
    * поток: 2 prop - false
    *
    * поток: 3 prop - false
    *
    * */
  /*  22. Что нужно знать об многопоточности:
        a. Что такое многопоточность?
        b. Как ведут себя многопоточные приложения?
        c. Что такое дополнительные потоки?
        d. Что такое фоновые потоки?
        e. Какие есть варианты создания потока?
        f. Какие есть варианты описания инструкций для потока?
        g. Какие у потока стадии жизненного цикла? (поток создан, поток передан планировщику (надо знать что именно планировщик управляет потоками) и т.д.)
        h. 100% надо знать про блок и метод synchronized, и в чем там может быть ошибка (что блок добавляется слишком много инструкций не связанных с блокировкой ресурса) и что приоритета в очереди НЕТ.
        l. В теории надо знать про методы wait(преостанавлевает работу потока на указанное количество милисекунд и что при вызове метода wait поток может проснуться в любой момент), notify (не будит конкретные потоки), notifyAll,
        m. Про sleep
        n. Про способы прерывания потока, когда поток будит остановлен или прерван методом interrupt
        o. В каких случаях выбрасывается InterruptedException
        p. И дополнительно многопоточные коллекции, блокирующие очереди и пулы потоков
*/
    public static void main(String[] args) {
        /* Для управления доступом к ресурсу семафор использует счетчик, представляющий количество разрешений.
           Если значение счетчика больше нуля, то поток получает доступ к ресурсу, при этом счетчик уменьшается на
           единицу. После окончания работы с ресурсом поток освобождает семафор, и счетчик увеличивается на единицу. Если же
           счетчик равен нулю, то поток блокируется и ждет, пока не получит разрешение от семафора.*/

        // Semaphore(int permits)
        // Semaphore(int permits, boolean fair)
        // void acquire() throws InterruptedException
        // void acquire(int permits) throws InterruptedВxception
        // void release()
        // void release(int permits)

        Semaphore sem = new Semaphore(1, true); // 13. Смысл такой, есть какой то Semaphore(симофор), который содержит число (количество разрешений, у нас 1), ссылка (sem) на этот симофор должна быть у всех потоков, тогда перед тем => 14
        HashSet<String> commonSet = new HashSet<>(); // 9. Ресурс. 12. Можно было бы поступить и иначе, например: вместо HashSet(однопоточныйСет) могли бы воспользоваться многопоточным Set-ом, или синхронайзд блоком, что бы блокировать доспуп к ресурсу и что бы один поток мог изменять этот самый ресурс или lругой вариант синхронизации.
        Runnable runnable = ()->{ // 10. задача
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " ожидает разрешение");
            try {
                sem.acquire(); // 15. перед тем как изменить общий ресурс, мы обращаемся к симофору и он отнимает единицу от 13го комментария "..... Semaphore(1, true)"; то есть от ЕДИНИЦЫ, следовательно, отнимает 1-1, получает 0 и все потоки будут заблокированы пока разрешение не станет положительным числом => 16.
                Thread.sleep(3000);
                System.out.println(threadName + " разрешение получено");
                commonSet.add(threadName); // 14. как обратиться к общему ресурсу (НЕ потокобезапасному, если бы был потокобезопасный то можно было бы просто обратиться к нему и записать в него), => 15
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(threadName + " освобождает разрешение");
                sem.release(); //17. После того как разрешенный поток, поработал задачу до этой строчки, то к сылке симофора прибовляют еденицу, и следующий поток помет быть добавлен в ресурс.
            }
        };
        new Thread(runnable).start(); // 16. допустим этот поток забрал разрешение, то остальным потоки не смогут обращаться к ресурсу HashSet.
        new Thread(runnable).start(); //11. Три потока, выполняющие одни и те же инструкции, обратиться к HashSet и добавить туда инфо.
        new Thread(runnable).start();
        // 18. Смысл такого подхода, что у разрешений есть флаг справедливости, например, первый поток захватил разрешение первым, второй становится в очередь, третий за вторым и т.д., то есть РАЗРЕШИНИЯ будут выдаваться по очереди. А не как попала, по типу синхроныйзд блока.
    }
}