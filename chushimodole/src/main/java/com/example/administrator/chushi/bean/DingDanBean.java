package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/20.
 */

public class DingDanBean {


    /**
     * code : 200
     * msg : 成功
     * data : [{"id":"1721","order_price":"20.00","status":"1","a_id":"0","point":"0","number":"150943247922336005","yun_price":"10.00","shixiao_time":"1509433381","peisong_name":"刘帅配送点","tui_status":"5","order_num":"1","title":"新疆香蕉2","product_num":"1","product_price":"10.00","unit":"500g","pic":"http://114.215.83.139/chushi/uploads/product/tehui/tupian2.png"},{"id":"1922","order_price":"108.00","status":"1","a_id":"0","point":"0","number":"150970674930238925","yun_price":"8.00","shixiao_time":"1509707650","peisong_name":"刘帅配送点","tui_status":"5","order_num":"1","title":"测试test","product_num":"1","product_price":"100.00","unit":"公斤","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fc1900acb2b.png"},{"id":"2401","order_price":"15.00","status":"1","a_id":"0","point":"0","number":"151030857118156491","yun_price":"3.00","shixiao_time":"1510309473","peisong_name":"刘帅配送点","tui_status":"5","order_num":"1","title":"测试23","product_num":"1","product_price":"12.00","unit":"g","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fc903ae321d.jpg"},{"id":"3161","order_price":"15.00","status":"1","a_id":"0","point":"0","number":"151125691637326364","yun_price":"3.00","shixiao_time":"1511257818","peisong_name":"测试-7","tui_status":"5","order_num":"2","title":"测试23","product_num":"1","product_price":"12.00","unit":"g","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fc903ae321d.jpg"},{"id":"3441","order_price":"5.10","status":"1","a_id":"0","point":"7390","number":"151141956534187263","yun_price":"5.00","shixiao_time":"1511420466","peisong_name":"测试-7","tui_status":"0","order_num":"3","title":"最新款的香蕉","product_num":"1","product_price":"50.00","unit":"个","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171025/59f074dc6d219.jpg"}]
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
         * id : 1721
         * order_price : 20.00
         * status : 1
         * a_id : 0
         * point : 0
         * number : 150943247922336005
         * yun_price : 10.00
         * shixiao_time : 1509433381
         * peisong_name : 刘帅配送点
         * tui_status : 5
         * order_num : 1
         * title : 新疆香蕉2
         * product_num : 1
         * product_price : 10.00
         * unit : 500g
         * pic : http://114.215.83.139/chushi/uploads/product/tehui/tupian2.png
         */

        private String id;
        private String order_price;
        private String status;
        private String a_id;
        private String point;
        private String number;
        private String yun_price;
        private String shixiao_time;
        private String peisong_name;
        private String tui_status;
        private String order_num;
        private String title;
        private String product_num;
        private String product_price;
        private String unit;
        private String pic;
        private int buneng;

        public int getBuneng() {
            return buneng;
        }

        public void setBuneng(int buneng) {
            this.buneng = buneng;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getA_id() {
            return a_id;
        }

        public void setA_id(String a_id) {
            this.a_id = a_id;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getYun_price() {
            return yun_price;
        }

        public void setYun_price(String yun_price) {
            this.yun_price = yun_price;
        }

        public String getShixiao_time() {
            return shixiao_time;
        }

        public void setShixiao_time(String shixiao_time) {
            this.shixiao_time = shixiao_time;
        }

        public String getPeisong_name() {
            return peisong_name;
        }

        public void setPeisong_name(String peisong_name) {
            this.peisong_name = peisong_name;
        }

        public String getTui_status() {
            return tui_status;
        }

        public void setTui_status(String tui_status) {
            this.tui_status = tui_status;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getProduct_num() {
            return product_num;
        }

        public void setProduct_num(String product_num) {
            this.product_num = product_num;
        }

        public String getProduct_price() {
            return product_price;
        }

        public void setProduct_price(String product_price) {
            this.product_price = product_price;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
