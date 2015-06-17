package com.messages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alexandr on 17.06.15.
 */
public class Queue {

    private long sizeLimit;
    private long timeLimit;
    private Map<String, Message> messages;

    public Queue(){
        messages = new HashMap<String, Message>();
    }
    public Queue(long sizeLimit, long timeLimit){
        this.sizeLimit = sizeLimit;
        this.timeLimit = timeLimit;
        messages = new HashMap<String, Message>();
    }

    private Map<String, Message> sortQueue(Map<String, Message> messages){
        return messages;
    }
    private long deleteMessage(){
        return 0;
    }

    public boolean sentMessage(String address, Message message) throws SendException {
        return true;
    }
    public boolean sentMessage(String address, List<Message> message) throws SendException {
        return true;
    }

    public long getQueueCount(){
        return 0;
    }


    public long getSizeLimit(){
        return sizeLimit;
    }
    public long getTimeLimit(){
        return timeLimit;
    }
    public void setSizeLimit(long sizeLimit){
        this.sizeLimit = sizeLimit;
    }
    public void setTimeLimit(long timeLimit){
        this.timeLimit = timeLimit;
    }

}
