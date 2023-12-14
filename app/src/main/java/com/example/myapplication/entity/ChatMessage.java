package com.example.myapplication.entity;

public class ChatMessage {
    private String content;
    private boolean isBot;
    private boolean animated = false;
    private boolean isSpecialMessage;

    public ChatMessage(String content, boolean isBot) {
        this.content = content;
        this.isBot = isBot;
    }

    public ChatMessage() {

    }

    public String getContent() {
        return content;
    }

    public boolean isBot() {

        return isBot;
    }
    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }
    public boolean isSpecialMessage() {
        return isSpecialMessage;
    }

    public void setSpecialMessage(boolean specialMessage) {
        isSpecialMessage = specialMessage;
    }
    // 添加一个构造函数



    public void setContent(String content) {
        this.content = content;
    }



    public void setBot(boolean bot) {
        isBot = bot;
    }

}
