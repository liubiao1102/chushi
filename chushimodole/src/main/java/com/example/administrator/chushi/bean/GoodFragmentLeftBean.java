package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class GoodFragmentLeftBean {

    /**
     * code : 200
     * msg : 成功
     * data : [{"id":"1","pic":"http://114.215.83.139/chushi/uploads/type/1.png","name":"水果"},{"id":"2","pic":"http://114.215.83.139/chushi/uploads/type/2.png","name":"肉类"},{"id":"3","pic":"http://114.215.83.139/chushi/uploads/type/3.png","name":"干货"},{"id":"4","pic":"http://114.215.83.139/chushi/uploads/type/4.png","name":"调味品"},{"id":"5","pic":"http://114.215.83.139/chushi/uploads/type/5.png","name":"半成品"}]
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
         * id : 1
         * pic : http://114.215.83.139/chushi/uploads/type/1.png
         * name : 水果
         */

        private String id;
        private String pic;
        private String name;
        private boolean isChecked;


        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }
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
