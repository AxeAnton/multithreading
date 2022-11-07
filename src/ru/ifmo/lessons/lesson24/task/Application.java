package ru.ifmo.lessons.lesson24.task;

import java.util.ArrayList;

public class Application {
    public static void main(String[] args)  {
        Account account1 = new Account(01, 1000);
        Account account2 = new Account(02, 2000);
        Account account3 = new Account(03, 3000);

        Bank tran = new Bank();
        tran.transferMoney(account1, account2, 100);
        tran.transferMoney(account1, account3, 200);
        tran.transferMoney(account3, account2, 100);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        System.out.println(account1.getBalance() + " " + account2.getBalance() + " " + account3.getBalance());

/*
        Thread daemon = new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println(account1.getBalance()+ " " + account2.getBalance()+ " " + account3.getBalance());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        daemon.setDaemon(true);
        daemon.start();
*/

    }
}