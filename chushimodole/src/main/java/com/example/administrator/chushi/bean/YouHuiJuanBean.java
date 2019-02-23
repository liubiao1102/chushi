package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/20.
 */

public class YouHuiJuanBean {


    /**
     * code : 200
     * data : {"used":[{"endtime":"2017-10-31","id":"15","money":"20","rule":"满100减20","rule_money":"100.00","shengyu":6,"status":"3","ticket_user_id":"69","title":"测试优惠券"}],"wuxiao":[{"endtime":"2017-10-31","id":"15","money":"20","rule":"满100减20","rule_money":"100.00","shengyu":6,"status":"2","ticket_user_id":"68","title":"测试优惠券"}],"youxiao":[{"endtime":"2033-05-18","id":"1","money":"16","rule":"满100","rule_money":"100.00","shengyu":5685,"status":"1","ticket_user_id":"4","title":"全民红包"},{"endtime":"2033-05-18","id":"3","money":"36","rule":"满300","rule_money":"300.00","shengyu":5685,"status":"1","ticket_user_id":"8","title":"扫码红包"},{"endtime":"2033-05-18","id":"14","money":"1","rule":"科技实力的风格","rule_money":"1.00","shengyu":5685,"status":"1","ticket_user_id":"36","title":"觉得是客户公司规定"},{"endtime":"2017-10-31","id":"15","money":"20","rule":"满100减20","rule_money":"100.00","shengyu":6,"status":"1","ticket_user_id":"53","title":"测试优惠券"}]}
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
        private List<UsedBean> used;
        private List<WuxiaoBean> wuxiao;
        private List<YouxiaoBean> youxiao;

        public List<UsedBean> getUsed() {
            return used;
        }

        public void setUsed(List<UsedBean> used) {
            this.used = used;
        }

        public List<WuxiaoBean> getWuxiao() {
            return wuxiao;
        }

        public void setWuxiao(List<WuxiaoBean> wuxiao) {
            this.wuxiao = wuxiao;
        }

        public List<YouxiaoBean> getYouxiao() {
            return youxiao;
        }

        public void setYouxiao(List<YouxiaoBean> youxiao) {
            this.youxiao = youxiao;
        }

        public static class UsedBean {
            /**
             * endtime : 2017-10-31
             * id : 15
             * money : 20
             * rule : 满100减20
             * rule_money : 100.00
             * shengyu : 6
             * status : 3
             * ticket_user_id : 69
             * title : 测试优惠券
             */

            private String endtime;
            private String id;
            private String money;
            private String rule;
            private String rule_money;
            private int shengyu;
            private String status;
            private String ticket_user_id;
            private String title;
            private String diejia;

            public String getDiejia() {
                return diejia;
            }

            public void setDiejia(String diejia) {
                this.diejia = diejia;
            }
            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getRule() {
                return rule;
            }

            public void setRule(String rule) {
                this.rule = rule;
            }

            public String getRule_money() {
                return rule_money;
            }

            public void setRule_money(String rule_money) {
                this.rule_money = rule_money;
            }

            public int getShengyu() {
                return shengyu;
            }

            public void setShengyu(int shengyu) {
                this.shengyu = shengyu;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTicket_user_id() {
                return ticket_user_id;
            }

            public void setTicket_user_id(String ticket_user_id) {
                this.ticket_user_id = ticket_user_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class WuxiaoBean {
            /**
             * endtime : 2017-10-31
             * id : 15
             * money : 20
             * rule : 满100减20
             * rule_money : 100.00
             * shengyu : 6
             * status : 2
             * ticket_user_id : 68
             * title : 测试优惠券
             */

            private String endtime;
            private String id;
            private String money;
            private String rule;
            private String rule_money;
            private int shengyu;
            private String status;
            private String ticket_user_id;
            private String title;
            private String diejia;

            public String getDiejia() {
                return diejia;
            }

            public void setDiejia(String diejia) {
                this.diejia = diejia;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getRule() {
                return rule;
            }

            public void setRule(String rule) {
                this.rule = rule;
            }

            public String getRule_money() {
                return rule_money;
            }

            public void setRule_money(String rule_money) {
                this.rule_money = rule_money;
            }

            public int getShengyu() {
                return shengyu;
            }

            public void setShengyu(int shengyu) {
                this.shengyu = shengyu;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTicket_user_id() {
                return ticket_user_id;
            }

            public void setTicket_user_id(String ticket_user_id) {
                this.ticket_user_id = ticket_user_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class YouxiaoBean {
            /**
             * endtime : 2033-05-18
             * id : 1
             * money : 16
             * rule : 满100
             * rule_money : 100.00
             * shengyu : 5685
             * status : 1
             * ticket_user_id : 4
             * title : 全民红包
             */

            private String endtime;
            private String id;
            private String money;
            private String rule;
            private String rule_money;
            private int shengyu;
            private String status;
            private String ticket_user_id;
            private String title;
            private String diejia;

            public String getDiejia() {
                return diejia;
            }

            public void setDiejia(String diejia) {
                this.diejia = diejia;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getRule() {
                return rule;
            }

            public void setRule(String rule) {
                this.rule = rule;
            }

            public String getRule_money() {
                return rule_money;
            }

            public void setRule_money(String rule_money) {
                this.rule_money = rule_money;
            }

            public int getShengyu() {
                return shengyu;
            }

            public void setShengyu(int shengyu) {
                this.shengyu = shengyu;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTicket_user_id() {
                return ticket_user_id;
            }

            public void setTicket_user_id(String ticket_user_id) {
                this.ticket_user_id = ticket_user_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
