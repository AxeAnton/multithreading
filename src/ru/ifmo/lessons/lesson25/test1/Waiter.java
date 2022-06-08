package ru.ifmo.lessons.lesson25.test1;



import java.util.concurrent.ArrayBlockingQueue;

public class Waiter implements Runnable{
    ArrayBlockingQueue<Oder> queue1;
    ArrayBlockingQueue<Oder> queue2;

    public Waiter(ArrayBlockingQueue<Oder> queue1, ArrayBlockingQueue<Oder> queue2) {
        this.queue1 = queue1;
        this.queue2 = queue2;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted())
        {
            String[] oders = {"заказ 1", "заказ 2", "заказ 3"};
            try {
                Oder oder = queue1.take();
                System.out.println("заказ из очереди 2" + oder);

//                Thread.sleep(5000);
                String oderNum = oders[(int) (Math.random() * oders.length)];
                oder.setOder(oderNum);
                //Oder oder = new Oder (oderNum);
                queue2.put(oder);
                System.out.println("заказ добавлен в очередь 3" + oder);

            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt(); //аккуратно завершает
            }
        }
    }
}