package com.example.administrator.chushi.bean;

/**
 * Created by Administrator on 2017/9/15.
 */

public class LoginBean {


    /**
     * code : 200
     * data : {"face":"","gender":"1","id":"4","mobile":"17600450868","token":"15054434084","truename":"","username":""}
     * msg : 登录成功
     */

    private int code;
    private DataBean data;
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * face :
         * gender : 1
         * id : 4
         * mobile : 17600450868
         * token : 15054434084
         * truename :
         * username :
         */

        private String face;
        private String gender;
        private String id;
        private String mobile;
        private String token;
        private String truename;
        private String username;

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
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

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
