package ru.ifmo.lessons.lesson23.test1;

import java.util.concurrent.CopyOnWriteArrayList;

public class AppTest {
    public static void main(String[] args) {


        CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();
        Flow1 flow1 = new Flow1(strings);
        flow1.start();


        Flow2 flow2 = new Flow2(strings);
        flow2.start();
        // один поток получает данные из консоли и добовляет их в string
        // другой поток простанавливает работу на 30 сек, после чего записывает в файл минимальную по размеру строку из string (строку удалить из колекции)






    }
}
