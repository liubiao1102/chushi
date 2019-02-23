package com.example.administrator.chushi.bean;

/**
 * Created by Administrator on 2017/9/8.
 */
public class EventMessage {
    private int num;
    private String msg;
    private int CheckNum;

    public int getCheckNum() {
        return CheckNum;
    }

    public void setCheckNum(int checkNum) {
        CheckNum = checkNum;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
