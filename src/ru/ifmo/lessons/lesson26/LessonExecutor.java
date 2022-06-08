package ru.ifmo.lessons.lesson26;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LessonExecutor extends ThreadPoolExecutor { // 28. ThreadPoolExecutor используется для расширения. Наш класс LessonExecutor расширяет встроенный пул ThreadPoolExecutor.

    // 29.  Коструктор LessonExecutor может содержать кучу всякой информации конструктор может принимать различные свойства или ни чего.
    public LessonExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) { // часто очередь для задач может быть свойством класса (со значение по умолчанию)  - BlockingQueue<Runnable> workQueue (ссылка на очередь).
    // 30. Наследование заставляет нас вызвать конструктор родителей super необходим т.к класс наследуется, так же в него можно внести конкретные значения, не только ссылки.
        super(corePoolSize, // 31. минимальное количество потоков
                maximumPoolSize, // 32. максимальное количество потоков
                keepAliveTime,  // 33. как долго избыточнвые потоки будут простаивать перед удалением
                unit, // 34. единицы измерения, относятся к пункту keepAliveTime
                workQueue // 35. очередь для задач, которые будут добавляться в пул (не понял до конца про этот пункт).
                // 36. может быть шестой аргумент - ThedeFactory класс который описывается как объекты создаются в пуле (new Thede вызывается в ручную)
                // 37. 7й может быть - реакция обрабочик на вызов методов после того как пул закрыт.
            );
        }
    // наш класс расширяет встроенный пул, т.к пулы в программе представлены этим классом - extends ThreadPoolExecutor ().
    // 38. класс может принимать любое количество методов, сетторов и конструкторов.
    //39. в классе можно переопределить любые доступные методы родителя (доступный, согласно модификаторам доступа), например:
@Override // 40. их очень много.
    protected  void  beforeExecute(Thread t,Runnable r) { // 41. методов много и они разные, можно много чего переопределить(согласно модификаторам доступа). Но это надо хорошо в них разобраться.
    System.out.println("BEFORE");






    }

}
