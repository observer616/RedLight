package com.ryan.redlight.entity;

/**
 * @author Ryan
 */
public class Msg {
    private String head;
    private String info;

    public Msg() {

    }

    public Msg(String head, String info) {
        this();
        this.head = head;
        this.info = info;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
