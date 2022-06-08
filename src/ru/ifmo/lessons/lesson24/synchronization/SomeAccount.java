package ru.ifmo.lessons.lesson24.synchronization;

public class SomeAccount {
    private  int balance;

    public int getBalance() {
        return balance;
    }

    // 13. synchronizedметод блокирует монитор объекта у которого вызывается метод
    public  /*synchronized*/ void upBalance(int count){
        balance +=count;
    } // 12. увеличивает баланс на 10 ед, после прохождения каждого потока.


}
