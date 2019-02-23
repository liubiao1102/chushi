package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19.
 */

public class AllXianBean {

    /**
     * code : 200
     * data : [{"id":"170101","name":"辖小店区","pid":"1701"},{"id":"170102","name":" 迎泽区","pid":"1701"},{"id":"170103","name":"杏花岭区","pid":"1701"},{"id":"170104","name":"尖草坪区","pid":"1701"},{"id":"170105","name":"万柏林区","pid":"1701"},{"id":"170106","name":"晋源区","pid":"1701"},{"id":"170107","name":"古交市","pid":"1701"},{"id":"170108","name":"清徐县","pid":"1701"},{"id":"170109","name":"阳曲县","pid":"1701"},{"id":"170110","name":"娄烦县","pid":"1701"}]
     * msg : 成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 170101
         * name : 辖小店区
         * pid : 1701
         */

        private String id;
        private String name;
        private String pid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }
    }
}
