package ru.ifmo.lessons.lesson23.test1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CopyOnWriteArrayList;

public class Flow2 extends  Thread{
    private CopyOnWriteArrayList<String> strings;


    public Flow2(CopyOnWriteArrayList<String> strings) {
        this.strings = strings;
    }

    @Override
    public void run() {

        while (true){
            try {
                Thread.sleep(3000);

//                String min = strings.get(0);
//                for (int i = 0; i < strings.size(); i++) {
//                    int s = strings.get(i).length();
//                    if (s < min.length()) {
//                        min = strings.get(i);
//                    }
//                }

                String minS = strings.stream().min((s1, s2) -> s1.length() - s2.length()).orElse("");
                strings.remove(minS);

                Files.write( //создает файл
                        Paths.get("file.txt"),  //куда записываются данные
                        minS.getBytes(), // 28. что записывается
                        StandardOpenOption.APPEND); // дозапись
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
