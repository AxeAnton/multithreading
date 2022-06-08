package ru.ifmo.lessons.lesson25;

import java.time.LocalDateTime;

public class Message {

    private  String  test;
    private LocalDateTime time;

    public Message(String test) {
        this.test = test;
        this.time = LocalDateTime.now();
    }
    public String getTest() {
        return test;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "test='" + test + '\'' +
                ", time=" + time +
                '}';
    }

}
