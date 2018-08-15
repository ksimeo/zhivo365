package com.ximeo.zhivo365.domain.dto;

import java.io.Serializable;

public class Message implements Serializable {
    private String type;
    private String message;

    public Message() {
        //NOP
    }

    public Message(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
