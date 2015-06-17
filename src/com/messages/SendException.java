package com.messages;

/**
 * Created by alexandr on 17.06.15.
 */
public class SendException extends Exception {

    public String timeOut(){
        return "превышение время ожидания без ответа";
    }

    public String sizeLimit(){
        return "достигнут предел размера очереди сообщений";
    }

}
