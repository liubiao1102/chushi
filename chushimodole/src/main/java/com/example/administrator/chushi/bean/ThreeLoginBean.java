package com.example.administrator.chushi.bean;

/**
 * Created by Administrator on 2017/10/11.
 */

public class ThreeLoginBean {

    /**
     * code : 200
     * data : {"addtime":"1505443408","edittime":"1507707064","encrypt":"","face":"http://114.215.83.139/chushi/uploads/user/4_1507707064.png","gender":"1","id":"4","is_lock":"0","level":"1","mobile":"17600450868","password":"","point":"954620","point_all":"10000000","qq":"3CA3DECB349319A759F2632D244BBCEF","token":"15077140374","truename":"","username":"夏天","weibo":"","weixin":""}
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
         * addtime : 1505443408
         * edittime : 1507707064
         * encrypt :
         * face : http://114.215.83.139/chushi/uploads/user/4_1507707064.png
         * gender : 1
         * id : 4
         * is_lock : 0
         * level : 1
         * mobile : 17600450868
         * password :
         * point : 954620
         * point_all : 10000000
         * qq : 3CA3DECB349319A759F2632D244BBCEF
         * token : 15077140374
         * truename :
         * username : 夏天
         * weibo :
         * weixin :
         */

        private String addtime;
        private String edittime;
        private String encrypt;
        private String face;
        private String gender;
        private String id;
        private String is_lock;
        private String level;
        private String mobile;
        private String password;
        private String point;
        private String point_all;
        private String qq;
        private String token;
        private String truename;
        private String username;
        private String weibo;
        private String weixin;

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getEdittime() {
            return edittime;
        }

        public void setEdittime(String edittime) {
            this.edittime = edittime;
        }

        public String getEncrypt() {
            return encrypt;
        }

        public void setEncrypt(String encrypt) {
            this.encrypt = encrypt;
        }

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

        public String getIs_lock() {
            return is_lock;
        }

        public void setIs_lock(String is_lock) {
            this.is_lock = is_lock;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getPoint_all() {
            return point_all;
        }

        public void setPoint_all(String point_all) {
            this.point_all = point_all;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
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

        public String getWeibo() {
            return weibo;
        }

        public void setWeibo(String weibo) {
            this.weibo = weibo;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }
    }
}
