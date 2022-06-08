package ru.ifmo.lessons.lesson23.base;


import java.util.concurrent.CopyOnWriteArrayList;

public class BaseInfo {
    public static void main(String[] args) {

        // основной поток программы называется main
        // запуск программы -Ю создание ппроцесса ОС - создается основной поток (main поток) -> начинается последовательно выполнение инструкций


        // git branch - список веток
        // git branch имя_ветки - создать ветку
        // git checkout имя_ветки - переключится на ветку



        // после создания основного потока можно запускать
        // дополнительные потоки, тогда инструкции процесса
        // будут выполняться параллельно
        // одно ядро процессора может обрабатывать один поток

        // жизненный цикл потока:
        // 1. NEW - поток создан (создан экземпляр класса Thread)
        // 2. RUNNABLE - управление потоком передается Thread Scheduler -
        // 'планировщику потоков jvm' (вызван метод start у экземпляра класса Thread)
        // 3. RUNNING - поток запущен планировщиком и начинает выполнять инструкции,
        // время запуска потока определяет сам 'планировщик потоков'
        // 4. NON-RUNNING (TIME WAITING, WAITING, BLOCKED) - поток может
        // находиться в состоянии ожидания
        // 5. TERMINATED - поток завершил работу

        // Варианты описания ИНСТРУКЦИЙ потока:
        // 1. создать класс, который наследуется от класса Thread,
        // инструкции, которые должен выполнять поток описываются в методе
        // public void run();
        // 2. инструкции, которые должен выполнять поток описываются в методе
        // public void run() интерфейса Runnable (при этом набор инструкций
        // можно описать в лямбда или создать отдельный класс)
        // 3. воспользоваться возможностями пакета java.util.concurrent.*

        MyThread myThread1 = new MyThread(); // 5. объект потока
        myThread1.setName("myThread 1");// 6. можно вызывать различные методы thread

        myThread1.start();  // 7. передача потока планировщику и вызов метода старт
       // myThread1.run(); // 8. они выполнятся но без паралельности патока


        MyTask myTask1 = new MyTask(); //14.  в таком виде объект потоком не является
        Thread thread = new Thread(myTask1); //15. передача объекта в поток, конструктор принемает на вход (аргумент) инструкции в виде объекта.
        thread.setName("myTask1"); // 16. имя задоное, вроде.
        thread.start();

        // 17. Реализация инструкции потока через лямбда выражение
        // 18. Runnable - функциональный интерфейс, поэтому интерфейс можно описать через лямбда выражение
        new Thread(() -> {
           System.out.println(Thread.currentThread().getName());
        }).start();


    }
}