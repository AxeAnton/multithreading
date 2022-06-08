package ru.ifmo.lessons.lesson23.base;

import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class JoinThears {
    public static void main(String[] args) {

        CopyOnWriteArrayList integers = new CopyOnWriteArrayList<>(); //19. пока не важно кто CopyOnWriteArrayList, считам, что это ArrayList
        Thread task1 = new Thread(() -> {
            try{
                //20. sleep - приостановка запись через 5000 милисек + сколько то, сколько решит планировщик
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task1 date is applay");
            integers.add(5000);
        });
        Thread task2 = new Thread(() -> {
            try{
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task2 date is applay");
            integers.add(3000);
        });
        Thread task3 = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter");
            int userNum = scanner.nextInt();
            System.out.println("task2 date is applay");
            integers.add(3000);
        });
        task1.start();
        task2.start();
        task3.start();
        //21. Из выше приведенных, main поток (сновной поток) НЕ ждать пока завершат работу не основные потоки.
        //22 Что бы они выводились одновременно, метод ожидания, вызывается в  try { } catch. Тоесть main ждет пока остальные завершатся.
        try {
            task1.join(); //23. ВАЖНО, что медод join (присоединение), вызывается у потока КОТОРОГО ждут (в main прописываем поток который ждет и у него join).
            task2.join();
            task3.join(10000); // 24. Если пользователь не введет данные, то поток завершит работу через 10 000 милисек.

        } catch (InterruptedException e){

        }


        System.out.println("main" + integers);
    }
}
