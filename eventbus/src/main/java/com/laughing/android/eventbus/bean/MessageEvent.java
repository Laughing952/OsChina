package com.laughing.android.eventbus.bean;

/**
 * 作者：Laughing on 2017/8/15 14:45
 * 邮箱：719240226@qq.com
 */

public class MessageEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
