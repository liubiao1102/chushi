package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class TuiKuanListsBean {


    /**
     * code : 200
     * data : [{"a_id":"0","id":"1616","number":"150933529555814334","order_num":"2","order_price":"210.00","peisong_name":"刘帅配送点","pic":"http://114.215.83.139/chushi/uploads/product/yingyang/shangp2.png","point":"20990","product_num":"1","product_price":"12.00","shixiao_time":"1509429600","status":"3","title":"1新疆香蕉","tui_status":"3","unit":"500g","yun_price":"8.00"},{"a_id":"0","id":"1721","number":"150943247922336005","order_num":"1","order_price":"20.00","peisong_name":"刘帅配送点","pic":"http://114.215.83.139/chushi/uploads/product/tehui/tupian2.png","point":"0","product_num":"1","product_price":"10.00","shixiao_time":"1509433381","status":"1","title":"新疆香蕉2","tui_status":"3","unit":"500g","yun_price":"10.00"},{"a_id":"0","id":"1922","number":"150970674930238925","order_num":"1","order_price":"108.00","peisong_name":"刘帅配送点","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fc1900acb2b.png","point":"0","product_num":"1","product_price":"100.00","shixiao_time":"1509707650","status":"1","title":"测试test","tui_status":"3","unit":"公斤","yun_price":"8.00"},{"a_id":"0","id":"2134","number":"151002413020257531","order_num":"1","order_price":"10.00","peisong_name":"刘帅配送点","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fc578561d5d.jpg","point":"0","product_num":"1","product_price":"0.00","shixiao_time":"1510025032","status":"3","title":"测试","tui_status":"3","unit":"于","yun_price":"10.00"},{"a_id":"0","id":"2136","number":"151002415168794204","order_num":"1","order_price":"98.00","peisong_name":"刘帅配送点","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fc1900acb2b.png","point":"0","product_num":"1","product_price":"100.00","shixiao_time":"1510025057","status":"3","title":"测试test","tui_status":"3","unit":"公斤","yun_price":"3.00"},{"a_id":"0","id":"2138","number":"151002416796798665","order_num":"1","order_price":"100000000.00","peisong_name":"刘帅配送点","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171106/59ffe5ea59362.jpg","point":"0","product_num":"1","product_price":"12.00","shixiao_time":"1510025068","status":"3","title":"测试11-6","tui_status":"4","unit":"于","yun_price":"99999999.99"},{"a_id":"0","id":"2140","number":"151002418023915164","order_num":"1","order_price":"100000000.00","peisong_name":"刘帅配送点","pic":"http://114.215.83.139/chushi/uploads/product/tehui/tupian2.png","point":"0","product_num":"1","product_price":"10.00","shixiao_time":"1510025083","status":"3","title":"新疆香蕉2","tui_status":"3","unit":"500g","yun_price":"99999999.99"}]
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
         * a_id : 0
         * id : 1616
         * number : 150933529555814334
         * order_num : 2
         * order_price : 210.00
         * peisong_name : 刘帅配送点
         * pic : http://114.215.83.139/chushi/uploads/product/yingyang/shangp2.png
         * point : 20990
         * product_num : 1
         * product_price : 12.00
         * shixiao_time : 1509429600
         * status : 3
         * title : 1新疆香蕉
         * tui_status : 3
         * unit : 500g
         * yun_price : 8.00
         */

        private String a_id;
        private String id;
        private String number;
        private String order_num;
        private String order_price;
        private String peisong_name;
        private String pic;
        private String point;
        private String product_num;
        private String product_price;
        private String shixiao_time;
        private String status;
        private String title;
        private String tui_status;
        private String unit;
        private String yun_price;

        public String getA_id() {
            return a_id;
        }

        public void setA_id(String a_id) {
            this.a_id = a_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public String getPeisong_name() {
            return peisong_name;
        }

        public void setPeisong_name(String peisong_name) {
            this.peisong_name = peisong_name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
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

        public String getShixiao_time() {
            return shixiao_time;
        }

        public void setShixiao_time(String shixiao_time) {
            this.shixiao_time = shixiao_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTui_status() {
            return tui_status;
        }

        public void setTui_status(String tui_status) {
            this.tui_status = tui_status;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getYun_price() {
            return yun_price;
        }

        public void setYun_price(String yun_price) {
            this.yun_price = yun_price;
        }
    }
}
