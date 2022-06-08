package ru.ifmo.lessons.lesson24.synchronization;
//17 Важно, что вложенные блокировки должны быть последовательны 1 и 2 И 1 и 2, а не 2 и 1 И 1 и 2 (иначе они будут ждать друг друга и не выполнится программа).
public class DeadLock { // 16. взаимная блокеровка потоков.
    public static void main(String[] args) {

        Object object1 = new Object();
        Object object2 = new Object();

        Thread thread1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " start");

            synchronized (object1){
                try {
                    System.out.println("Action thread1 над object2");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2){
                    System.out.println("thread1 -> object2 and object1");
                }
            }
        });

        Thread thread2 = new Thread(()->{

            System.out.println(Thread.currentThread().getName() + " запущен");
            synchronized (object1){
                try {
                    System.out.println("Action thread2 над object1");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2){
                    System.out.println("thread2 -> object2 and object1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}