package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/21.
 */

public class MessagenoxqBean {

    /**
     * code : 200
     * msg : 成功
     * data : [{"id":"11","content":"恭喜你获得了1张优惠券的的的的的的的,有限期为2017-12-03 00:00:00","addtime":"2017-11-21 11:31","status":"1"},{"id":"12","content":"你有一张优惠券的的的的的的的于7天后过期","addtime":"2017-11-21 11:31","status":"1"}]
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
         * id : 11
         * content : 恭喜你获得了1张优惠券的的的的的的的,有限期为2017-12-03 00:00:00
         * addtime : 2017-11-21 11:31
         * status : 1
         */

        private String id;
        private String content;
        private String addtime;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
