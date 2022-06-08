package ru.ifmo.lessons.lesson24.synchronization;

public class Increment extends Thread{ // 2. Поток

    private SomeAccount account;

    public Increment(SomeAccount account) {
        this.account = account;
    }

    @Override
    public  void run() { // 3. Переопределили run

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 14 (бывшая 5.). synchronized блок - блокирует монитор объекта из ( ) в данном случае account, затем выполняется то что в скобках - {}
        synchronized (account) {
           account.upBalance(10); // 15 (бывшая 4.). в синхронайст блоке не должно быть ни чего что не связано с изменением свойств объекта. Поток доходит до вызова метода он видит, что аккаунт синхронизирован  - .upBalance(), блакирует аккаунт выполняет необходимые инструкции и разблакирует аккаут, следующий поток захватывает аккаунт.
        }
        }
    }

