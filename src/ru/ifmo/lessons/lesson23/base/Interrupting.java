package ru.ifmo.lessons.lesson23.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Interrupting {
    public static void main(String[] args) {

        // 25. фоновый поток, завершает работу, когда завершат работу все НЕ фоновые потоки public void run() {}. Например в class JoinThears, поток номер три если бы пользователь ни чего не вводил, так и продолжал работать после завершения работы всех потоков, а вот если бы он был фоновым то завершил бы работу, сразу после завершения работы последнего(вообще предпоследнего, т.к фоновы он сам последний) потока.
        Thread daemon = new Thread(() -> {
            try {
                Thread.sleep(5000);
                Files.write( //26. создает файл
                        Paths.get("file.txt"),  //27.куда записываются данные
                        "collected dates".getBytes(), // 28. что записывается
                        StandardOpenOption.APPEND); // 29. дозапись

            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        });

        daemon.setDaemon(true); // 30. setDaemon - поток становится фоновым

        daemon.start();


        // Прерывание потока:
        // 1. если выброшено неоработанное исключение
        // 2. остановилась jvm
        // 3. когда выполнены все инструкции
        // 4. если это фоновый поток и все НЕ фоновые потоки завершили
        // работу

        Thread actions = new Thread(() -> { //31. механизм остановвки потока
            while (!Thread.currentThread().isInterrupted()){ //32. isInterrupted - изначально имеет значение false
                System.out.println("some action...");
                try {
                    Thread.sleep(1000); //33. какието инструкции
                } catch (InterruptedException e) { // 33.5 прирывания потока во время сна вызовет ошибку, такое почему то часто бывает.
                    Thread.currentThread().interrupt(); // 34. Вообщем если прервать поток во время сна, то он 35 поменяется на False, что бы изменить сново надо вот эта строчка.

                }
            }
        });
        actions.start();


        actions.interrupt(); // interrupted = true // 35. Это строчка меняет boolen значение в 32.
    }
}

