package com.ryan.redlight.entity;

/**
 * @author Ryan
 */
public class Msg {
    public final static String STATE_SUCCESS = "success";
    public final static String STATE_FAILURE = "failure";
    private String state;
    private String msg;
    private Integer code;

    public Msg() {
    }

    public Msg(String state) {
        setState(state);
    }

    public Msg(String state, String msg) {
        setState(state);
        setMsg(msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    ;
}
