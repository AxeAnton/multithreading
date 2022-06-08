package ru.ifmo.lessons.lesson23.base;


public class MyThread extends Thread { // 1. обязательно наследование Thread
    // 2. в данном классе могут быть любые методы, конструкторы и свойства,

    // 3. все что в методе run это отдельный поток
    @Override
    public void run() {
        System.out.println(this.getName());
        System.out.println(Thread.currentThread().getName()); // 4. статический метод currentThread() ссылка на ТЕКУЩИЙ метод. В ком потоке вызывае, ссылку на этот поток и возвращает currentThread()

    }
}
