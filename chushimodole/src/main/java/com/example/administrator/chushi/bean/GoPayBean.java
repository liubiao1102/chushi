package com.example.administrator.chushi.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/21.
 */

public class GoPayBean implements Serializable{

    /**
     * msg : 成功
     * code : 200
     * data : {"number":"MD150587509821575742","shixiao_time":"1505876055","price":"8.00","id":"275"}
     */
    private String msg;
    private int code;
    private DataEntity data;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity implements Serializable{
        /**
         * number : MD150587509821575742
         * shixiao_time : 1505876055
         * price : 8.00
         * id : 275
         */
        private String number;
        private String shixiao_time;
        private String price;
        private String id;

        public void setNumber(String number) {
            this.number = number;
        }

        public void setShixiao_time(String shixiao_time) {
            this.shixiao_time = shixiao_time;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNumber() {
            return number;
        }

        public String getShixiao_time() {
            return shixiao_time;
        }

        public String getPrice() {
            return price;
        }

        public String getId() {
            return id;
        }
    }
}
