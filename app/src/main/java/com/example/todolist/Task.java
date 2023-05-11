package com.example.todolist;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    private String name;
    private String detail;
    private Date date;
    private boolean done;
    private int id;
    public Task () {};
    public Task(String name, String detail, Date date, boolean done)
    {
        this.date= date;
        this.detail = detail;
        this.name = name;
        this.done = done;
    }
    public void setDetail(String detai1) {
        this.detail = detai1;
    }

    public String getDetail() {
        return detail;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
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
