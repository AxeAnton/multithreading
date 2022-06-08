package ru.ifmo.lessons.lesson26;

import ru.ifmo.lessons.lesson25.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableAndFuture {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool1 = Executors.newFixedThreadPool(3); //51. создали фиксированный пул на три потока

        Callable<Message> task = new MessageGenerator(); // 52. инструкция для задач

        ArrayList<Future<Message>> taskResulys = new ArrayList<>(); // 53. Т.к при работе с данным интерфейсом необходимо результаты задач куда то складывать, например какое то хронилище, то создаем
        // 54. то есть, 11 строка пул фиксированных потоков, 13 - задача, 15 это куда он положит результат своей работы.

        for (int i = 0; i < 10; i++) {
            //55. добавляем задачу в очередь на выполнение, сообщаем куда нужно передать результат работы потока,
            Future<Message> resultContainer = pool1.submit(task); // 56. NB метод submit передает задачу в очередь и возвращает экзампляр типа Furufe (контейнер с результатами работы над одной задачей)
            // 57. submit добавляет задачу в очередь pool1, начинается выполнение задачи и ее результат это сообщение, и сообщение поток положит вот в этот объект Future<Message>.
            taskResulys.add(resultContainer); // 58. затем объект добавляется в ArrayList

        }
/*        for (Future<Message> taskResult: taskResulys) {
            System.out.println("Ожидание получения данных");
            try {

            // 59. метод get приостанавливает работу потока пока данные в коробке не появятся
                System.out.println("Сообщение :" + taskResult.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            pool1.shutdown();
        }
*/

        for (Future<Message> taskResult: taskResulys) {
            System.out.println("Ожидание получения данных");
            try {
                System.out.println("Сообщение :" + taskResult.get((long) (Math.random() * 2000), TimeUnit.MICROSECONDS)); // 60. метод get приостанавливает работу потока пока данные в коробке не появятся, но через указанное время возьмет другой контейнер в обработку и не будет вообще возвращаться к ящику 1.
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) { // 61. Не получилось получить инфо
                System.out.println("Не смог дождаться результата");
            }
        }
        pool1.shutdown();
        // 62. БУДУЩИЕ сообщения.
        ExecutorService pool2 = Executors.newFixedThreadPool(3);
        List<Callable<Message>> tasksList = new ArrayList<>(); // 63. Список задая, а не одна!
        tasksList.add(new MessageGenerator());
        tasksList.add(new MessageGenerator());
        tasksList.add(new MessageGenerator());

        try {
            List<Future<Message>> resultsContainer = pool2.invokeAll(tasksList); // 64. NB invokeAll - принимает список задач, а не одну. Вся задница с предыдущим перебором
            pool2.shutdown(); // 65. говорим зчо больше задач не принимает
            for (Future<Message> container : resultsContainer) { // 66. проходимся по списку задач
               System.out.println("Сообщение " + container.get());
            }

        } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
        }
        // 67. задачу можно отменить future.cancel(true);
        // 68. Можно проверить, отменена ли задача future.isCancel();
        // 69. Можно проверить, выполнена ли задача future.id Done();
    }
}