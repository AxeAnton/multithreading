package ru.ifmo.lessons.lesson23.base;


public class MyTask implements Runnable{ //9. Так же необходимо имплеминтировать Runnable
    // 10. в данном классе могут быть любые методы, конструкторы и свойства,

    // 11. все что в методе run это отдельный поток
    @Override
    public void run() {
      //  12. System.out.println(this.getName()); не доступен потому что нет насладования Thread.
        System.out.println(Thread.currentThread().getName()); //13. статический метод currentThread() ссылка на ТЕКУЩИЙ метод.

    }
}
