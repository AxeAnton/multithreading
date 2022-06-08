package ru.ifmo.lessons.lesson25.test1;



import java.util.concurrent.ArrayBlockingQueue;

public class Castomer implements Runnable{
    ArrayBlockingQueue<Oder> queue1;
    ArrayBlockingQueue<Oder> queue3;

    public Castomer(ArrayBlockingQueue<Oder> queue1, ArrayBlockingQueue<Oder> queue3) {
        this.queue1 = queue1;
        this.queue3 = queue3;
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            String[] oders = {"заказ 1", "заказ 2", "заказ 3"};

            try {
                String oderNum = oders [(int) (Math.random()*oders.length)];
                Oder oder = new Oder(oderNum);
                System.out.println("заказ создан " + oder);

                queue1.put(oder);
                System.out.println("заказ добавлен в очередь 1" + oder);

                System.out.println(queue3.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt(); //аккуратно завершает
            }
        }


    }
}
