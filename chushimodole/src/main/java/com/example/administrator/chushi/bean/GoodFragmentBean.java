package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class GoodFragmentBean {


    /**
     * code : 200
     * data : [{"id":"2","name":"满200减20","pic":"http://114.215.83.139/chushi/uploads/activity/u180.jpg"},{"id":"5","name":"满200减20","pic":"http://114.215.83.139/chushi/uploads/activity/u180.jpg"},{"id":"3","name":"满100元","pic":"http://114.215.83.139/chushi/uploads/activity/u180.jpg"},{"id":"6","name":"满100元","pic":"http://114.215.83.139/chushi/uploads/activity/u180.jpg"}]
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
         * id : 2
         * name : 满200减20
         * pic : http://114.215.83.139/chushi/uploads/activity/u180.jpg
         */

        private String id;
        private String name;
        private String pic;

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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
