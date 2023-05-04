package com.example.todolist;

public class Task {
    private String name;
    private String date;
    private boolean done;
    private int id;
    public Task () {};
    public Task(String name, String date, boolean done)
    {
        this.date= date;
        this.name = name;
        this.done = done;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


}
