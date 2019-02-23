package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/11.
 */

public class BannerBean {

    /**
     * code : 200
     * data : [{"id":"2","picurl":"http://114.215.83.139/chushi/uploads/ad/banner_product.png","sort":"1","title":"商品banner1"}]
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
         * picurl : http://114.215.83.139/chushi/uploads/ad/banner_product.png
         * sort : 1
         * title : 商品banner1
         */

        private String id;
        private String picurl;
        private String sort;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
