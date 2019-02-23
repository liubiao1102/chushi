package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */

public class BannerBeanHome {

    /**
     * code : 200
     * datas : [{"id":"7","name":"活动专场测试","pic":""},{"id":"3","name":"满100元","pic":"/chushi/uploads/activity/u180.jpg"},{"id":"6","name":"满100元","pic":"/chushi/uploads/activity/u180.jpg"}]
     * msg : 成功
     */

    private int code;
    private String msg;
    private List<DatasBean> datas;

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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * id : 7
         * name : 活动专场测试
         * pic :
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
