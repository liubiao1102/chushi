package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19.
 */

public class AddressLieBiaoBean {

    /**
     * code : 200
     * data : [{"address":"测试地址666","area":"太原市，辖小店区，测试小区1","c_id":"1701","id":"5","mobile":"18710010271","status":"1","username":"孤雪狼丶沐风","v_id":"17010101","x_id":"170101"},{"address":"测试地址666","area":"太原市，辖小店区，测试小区1","c_id":"1701","id":"2","mobile":"18710010271","status":"0","username":"孤雪狼丶沐风","v_id":"17010101","x_id":"170101"},{"address":"测试地址","area":"太原市，辖小店区，测试小区1","c_id":"1701","id":"4","mobile":"18710010271","status":"0","username":"沐风","v_id":"17010101","x_id":"170101"},{"address":"测试地址2","area":"太原市，辖小店区，测试小区1","c_id":"1701","id":"3","mobile":"18710010271","status":"0","username":"沐风","v_id":"17010101","x_id":"170101"}]
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
         * address : 测试地址666
         * area : 太原市，辖小店区，测试小区1
         * c_id : 1701
         * id : 5
         * mobile : 18710010271
         * status : 1
         * username : 孤雪狼丶沐风
         * v_id : 17010101
         * x_id : 170101
         */

        private String address;
        private String area;
        private String c_id;
        private String id;
        private String mobile;
        private String status;
        private String username;
        private String v_id;
        private String x_id;
        private boolean isChecked;


        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getC_id() {
            return c_id;
        }

        public void setC_id(String c_id) {
            this.c_id = c_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getV_id() {
            return v_id;
        }

        public void setV_id(String v_id) {
            this.v_id = v_id;
        }

        public String getX_id() {
            return x_id;
        }

        public void setX_id(String x_id) {
            this.x_id = x_id;
        }
    }
}
