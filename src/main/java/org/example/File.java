package org.example;

import java.time.LocalDateTime;

public class File {
    private String name;
    private int size;
    private LocalDateTime date;

    public File(String name, int size, LocalDateTime date) {
        this.name = name;
        this.size = size;
        this.date = date;
    }
    public File(String name, int size){
        this.name = name;
        this.size = size;
        this.date = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }
    public int getSize() {
        return size;
    }
    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", date=" + date +
                '}';
    }
}
