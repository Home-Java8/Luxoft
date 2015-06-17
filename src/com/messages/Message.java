package com.messages;

import java.util.Date;

/**
 * Created by alexandr on 17.06.15.
 */
public class Message {

    private String message;
    private Date      date;
    private int   priority;
    private boolean status;

    public Message(){}
    public Message(String message){
        this.message  = message;
        this.date     = new Date();
        this.priority = 2;
        this.status   = true;
    }
    public Message(String message, Date date){
        this.message  = message;
        this.date     = date;
        this.priority = 2;
        this.status   = true;
    }
    public Message(String message, Date date, int priority){
        this.message  = message;
        this.date     = date;
        this.priority = priority;
        this.status   = true;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", priority=" + priority +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
