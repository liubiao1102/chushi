package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */

public class ShouCangLieBiaoBean {

    /**
     * code : 200
     * data : [{"collect_id":"14","kou_price":"0.00","pic":"http://114.215.83.139/chushi/uploads/product/yingyang/shangp2.png","product_id":"2","title":"新疆香蕉","yuan_price":"12.00","zhekou":"0"},{"collect_id":"7","kou_price":"0.00","pic":"http://114.215.83.139/chushi/uploads/product/yingyang/shangp2.png","product_id":"5","title":"新疆香蕉","yuan_price":"12.00","zhekou":"0"}]
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
         * collect_id : 14
         * kou_price : 0.00
         * pic : http://114.215.83.139/chushi/uploads/product/yingyang/shangp2.png
         * product_id : 2
         * title : 新疆香蕉
         * yuan_price : 12.00
         * zhekou : 0
         */

        private String collect_id;
        private String kou_price;
        private String pic;
        private String product_id;
        private String title;
        private String yuan_price;
        private String zhekou;

        public String getCollect_id() {
            return collect_id;
        }

        public void setCollect_id(String collect_id) {
            this.collect_id = collect_id;
        }

        public String getKou_price() {
            return kou_price;
        }

        public void setKou_price(String kou_price) {
            this.kou_price = kou_price;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getYuan_price() {
            return yuan_price;
        }

        public void setYuan_price(String yuan_price) {
            this.yuan_price = yuan_price;
        }

        public String getZhekou() {
            return zhekou;
        }

        public void setZhekou(String zhekou) {
            this.zhekou = zhekou;
        }
    }
}
