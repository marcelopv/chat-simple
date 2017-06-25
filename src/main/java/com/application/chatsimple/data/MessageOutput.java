package com.application.chatsimple.data;

public class MessageOutput {

    private String from;
    private String text;
    private String dateTime;

    public MessageOutput(){

    }

    public MessageOutput(String from, String text, String dateTime) {
        this.from = from;
        this.text = text;
        this.dateTime = dateTime;
    }

    public String getFrom() {
        return from;
    }

    public String getText() {
        return text;
    }

    public String getDateTime() {
        return dateTime;
    }
}
