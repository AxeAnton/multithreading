package ru.ifmo.lessons.lesson24.synchronization;

import java.util.ArrayList;

public class Apllication {
    public static void main(String[] args) {
        SomeAccount account = new SomeAccount();


        ArrayList<Increment> increments = new ArrayList<>(); // 5. создали объект
        for (int i = 0; i < 100; i++) { // 6. автоматически создали 100 объектов
            increments.add(new Increment(account)); // 7. образуется новый поток но он ссылается на одну ссылку.
        }

        for (Increment increment : increments) {
            increment.start(); // 8. запуск потоков (объектов)
        }

        for (Increment increment : increments) {
            try {
                increment.join(); // 9. join ждет пока все закончатся
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Balance:" + account.getBalance()); // 10. getBalance() - увеличивает баланс на 10 ед, смотри п12.
        }
    }
}