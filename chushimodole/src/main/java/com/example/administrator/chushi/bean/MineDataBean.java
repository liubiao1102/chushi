package com.example.administrator.chushi.bean;

/**
 * Created by Administrator on 2017/9/18.
 */

public class MineDataBean {

    /**
     * code : 200
     * data : {"face":"http://114.215.83.139/chushi/uploads/user/4_1511177452.png","gender":"男","id":"4","level":"1","mobile":"17600450868","point":"0","ticket":"2","truename":"","unread":"0","username":"吖"}
     * msg : 成功
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
         * face : http://114.215.83.139/chushi/uploads/user/4_1511177452.png
         * gender : 男
         * id : 4
         * level : 1
         * mobile : 17600450868
         * point : 0
         * ticket : 2
         * truename :
         * unread : 0
         * username : 吖
         */

        private String face;
        private String gender;
        private String id;
        private String level;
        private String mobile;
        private String point;
        private String ticket;
        private String truename;
        private String unread;
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

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getTicket() {
            return ticket;
        }

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getUnread() {
            return unread;
        }

        public void setUnread(String unread) {
            this.unread = unread;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
