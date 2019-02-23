package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */

public class GoodContentItemBean {


    /**
     * code : 200
     * msg : 成功
     * data : [{"id":"14","title":"测试商品","yuan_price":"10.00","zhekou":"0","kou_price":"9.00","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171023/59ed8de28b1ab.png","kucun":"100"}]
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
         * id : 14
         * title : 测试商品
         * yuan_price : 10.00
         * zhekou : 0
         * kou_price : 9.00
         * pic : http://114.215.83.139/chushi/uploads/product/putong/20171023/59ed8de28b1ab.png
         * kucun : 100
         */

        private String id;
        private String title;
        private String yuan_price;
        private String zhekou;
        private String kou_price;
        private String pic;
        private String kucun;
        private String price;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getKucun() {
            return kucun;
        }

        public void setKucun(String kucun) {
            this.kucun = kucun;
        }
    }
}
