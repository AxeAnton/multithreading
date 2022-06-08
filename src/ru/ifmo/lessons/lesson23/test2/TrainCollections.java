package ru.ifmo.lessons.lesson23.test2;

import java.util.ArrayList;
import java.util.Iterator;

public class TrainCollections {

    public static void main(String[] args) {
        // Метод sort() - сортировка в естественном порядке
        // 1. Создать массив строк
        ArrayList<String> AL = new ArrayList<String>();
        AL.add("jklm");
        AL.add("abc");
        AL.add("e");
        AL.add("lkls");
        AL.add("az");

        // 2. Вывести исходный массив
        Iterator<String> it = AL.iterator();
        System.out.print("AL => ");
        while (it.hasNext())
            System.out.print(it.next()+"  ");
        System.out.println();

        // 3. Отсортировать элементы массива, метод sort()
        AL.sort(null);

        // 4. Вывести отсортированный массив
        System.out.print("AL => ");
        for (int i=0; i<AL.size(); i++)
            System.out.print(AL.get(i) + "  ");
        System.out.println();
    }
}