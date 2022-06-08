package ru.ifmo.lessons.lesson23.test1;

import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Flow1 extends Thread {
    Scanner scanner;
    private CopyOnWriteArrayList<String> strings;

    public Flow1(CopyOnWriteArrayList<String> strings) {
        this.strings = strings;
    }

    public void addToStrings() {
        scanner = new Scanner(System.in);
        while (true){ // запускается бесконечный цикл
            System.out.println("Enter a string 1");
            String string = scanner.nextLine(); // сканер получает значение из консолки
            if (string != null) {
                strings.add(string);
            }
        }
    }
}
