package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class GoodFragmentContentBean {

    /**
     * code : 200
     * msg : 成功
     * data : [{"id":"6","pic":"http://114.215.83.139/chushi","name":"香蕉"},{"id":"7","pic":"http://114.215.83.139/chushi","name":"桃子"}]
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
         * id : 6
         * pic : http://114.215.83.139/chushi
         * name : 香蕉
         */

        private String id;
        private String pic;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
