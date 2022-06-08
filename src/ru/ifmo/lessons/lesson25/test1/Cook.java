package ru.ifmo.lessons.lesson25.test1;


import ru.ifmo.lessons.lesson25.Message;

import java.util.concurrent.ArrayBlockingQueue;

public class Cook implements Runnable{
    ArrayBlockingQueue<Oder> queue2;
    ArrayBlockingQueue<Oder> queue3;


    public Cook(ArrayBlockingQueue<Oder> queue2, ArrayBlockingQueue<Oder> queue3) {
        this.queue2 = queue2;
        this.queue3 = queue3;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            String[] oders = {"заказ 1", "заказ 2", "заказ 3"};
            try {
                Oder oder = queue2.take();
                System.out.println("сообщение из очереди 2" + oder);

//                Thread.sleep(5000);
                String oderNum = oders [(int) (Math.random()*oders.length)];
                oder.setOder(oderNum);
                //Oder oder = new Oder (oderNum);
                queue3.put(oder);
                System.out.println("заказ добавлен в очередь 3" + oder);

            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt(); //аккуратно завершает
            }
        }


    }

}
