package ru.ifmo.lessons.lesson24.task;

public class Bank {

    public void transferMoney(Account src, Account dst, int money) {
        Thread transaction = new Thread(new Transaction(src, dst, money));
        //transaction.setDaemon(true);
        transaction.start();


    }
}