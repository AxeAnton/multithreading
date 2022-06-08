package ru.ifmo.lessons.lesson27;

import java.util.concurrent.Exchanger;

import static java.lang.Thread.sleep;

public class ExchangerExample {
    public static void main(String[] args) {
//        1. Класс Exchanger предназначен для обмена данными между потоками.Он является типизированным и типизируется типом
//        2. данных, которыми потоки должны обмениваться.
//        3. Обмен данными производится с помощью единственного метода этого класса exchange():
        Exchanger<String> exchanger = new Exchanger<>(); // 4. Это коробочка где указан ТД который будет в коробки.
        new Thread(() -> {
            try {
                System.out.println("Первый поток получил данные: " + //5. у каждого потока должна быть ссылка на обменник, если реализация не через лямбда, то ссылка передается через конструктор или через сеттор.
                        exchanger.exchange("Первый поток")); // 7. Для передачи данных вызывается метод exchanger.exchange("Первый поток") и в него передаются данные которые поток хочет отдать, аналогично и второй поток, только после этого происходит обмен
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                sleep(3000);
                System.out.println("Второй поток получил данные: " + exchanger.exchange("Второй поток")); //6. у второго потока, так же должна быть ссылка на тот же объменник
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
