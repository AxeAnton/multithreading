package ru.ifmo.lessons.lesson25;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Task implements Delayed { // 19. implements Delayed и заставляет переопределить два метода public long getDelay и  public int compareTo
    private Runnable action;
    private LocalDateTime time; // 24. когда инструкции должны быть выполнены в отдельном потоке

    public Runnable getAction() {
        return action;
    }

     public Task(Runnable action, LocalDateTime time) { // 25. конструктор с временем и действиями
        this.action = action;
        this.time = time;
    }

    @Override// 22. Метод необходим, что бы выяснить, можно ли извлечь элемент методом Take(), если метод вернет "О" или отрицательное число, то элемент может быть извлечен из очереди, если положительное, значит нельзя и метод take заблокирует вызывающий поток.
    public long getDelay(TimeUnit unit) { // 23.сравниваем текущее время - LocalDateTime.now() и еще каким то - time
        return unit.convert(
                Duration.between(LocalDateTime.now(), time) // 23. convert - преобразовывает вычисления (во что то свое) и получаем, если время "0" или отрицательное, то можно доставать из очереди, если положительное то нет.
                        .getSeconds( // 23. Важный момент, какая единица вычисления -  ....getSeconds(),
                                ), TimeUnit.SECONDS);  // 23. Такая и в конверт должна уйти - .....TimeUnit.SECONDS)
    }

    @Override // 20. элементы в очереди будут храниться в отсортированном порядке
    public int compareTo(Delayed o) { //
        // 21. если получаем "-1" то объект будет стоять раньше в очереди, если "+1" то позже,
         LocalDateTime other = ((Task) o).time;  // 26. для сравнения необходимо объект "о" привести к ТД Task, но так как программа сама его повысила до Delayed. Теперь у объекта можно получить любые свойства класса time. Причем так как мы находимся в классе Task, то getter писать не надо.
        return this.time.compareTo(other); // 27. Время текущего объекта.

    }
}
