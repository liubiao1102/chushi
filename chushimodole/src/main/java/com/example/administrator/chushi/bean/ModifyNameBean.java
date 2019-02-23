package com.example.administrator.chushi.bean;

/**
 * Created by Administrator on 2017/9/18.
 */

public class ModifyNameBean {

    /**
     * code : 200
     * msg : 修改成功
     * data : {"id":"4","username":"123","truename":"","mobile":"17600450868","gender":"男","face":"","point":"0","level":"1","ticket":"0"}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 4
         * username : 123
         * truename :
         * mobile : 17600450868
         * gender : 男
         * face :
         * point : 0
         * level : 1
         * ticket : 0
         */

        private String id;
        private String username;
        private String truename;
        private String mobile;
        private String gender;
        private String face;
        private String point;
        private String level;
        private String ticket;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getTicket() {
            return ticket;
        }

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }
    }
}
