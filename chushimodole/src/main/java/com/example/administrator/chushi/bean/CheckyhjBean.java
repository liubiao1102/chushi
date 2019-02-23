package com.example.administrator.chushi.bean;

/**
 * Created by Administrator on 2017/10/20.
 */

public class CheckyhjBean {


    /**
     * code : 200
     * data : {"point":"1690.0","point_money":"16.90","total_price":"120","yun_price":"3.00"}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * point : 1690.0
         * point_money : 16.90
         * total_price : 120
         * yun_price : 3.00
         */

        private String point;
        private String point_money;
        private String total_price;
        private String yun_price;

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getPoint_money() {
            return point_money;
        }

        public void setPoint_money(String point_money) {
            this.point_money = point_money;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getYun_price() {
            return yun_price;
        }

        public void setYun_price(String yun_price) {
            this.yun_price = yun_price;
        }
    }
}
