package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/21.
 */

public class DingDanXQBean {

    /**
     * code : 200
     * data : {"agent":{"face":"http://114.215.83.139/chushi/uploads/agent/liushuai.png","id":"1","mobile":"13838383838","name":"刘帅","store":"1","store_mobile":"13833338888"},"info":[{"content":"","is_pinglun":"0","pic":"http://114.215.83.139/chushi/uploads/product/tehui/tupian2.png","product_num":"10","product_price":"100.00","star":"0","title":"新疆香蕉","unit":"500g"}],"order":{"a_id":"1","address":"aaa","addtime":"2017-09-20 16:39","fahuo_time":"1970-01-01 08:00","fukuan_time":"1970-01-01 08:00","id":"362","is_pinglun":"0","number":"MD150589678276129079","order_num":1,"peisong_star":"0","pinglun_content":"","price":"108.00","queren_time":"1970-01-01 08:00","shouhuo_mobile":"13513673246","shouhuo_name":"gzp ","status":"4","yun_price":"8.00"}}
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
         * agent : {"face":"http://114.215.83.139/chushi/uploads/agent/liushuai.png","id":"1","mobile":"13838383838","name":"刘帅","store":"1","store_mobile":"13833338888"}
         * info : [{"content":"","is_pinglun":"0","pic":"http://114.215.83.139/chushi/uploads/product/tehui/tupian2.png","product_num":"10","product_price":"100.00","star":"0","title":"新疆香蕉","unit":"500g"}]
         * order : {"a_id":"1","address":"aaa","addtime":"2017-09-20 16:39","fahuo_time":"1970-01-01 08:00","fukuan_time":"1970-01-01 08:00","id":"362","is_pinglun":"0","number":"MD150589678276129079","order_num":1,"peisong_star":"0","pinglun_content":"","price":"108.00","queren_time":"1970-01-01 08:00","shouhuo_mobile":"13513673246","shouhuo_name":"gzp ","status":"4","yun_price":"8.00"}
         */

        private AgentBean agent;
        private OrderBean order;
        private List<InfoBean> info;

        public AgentBean getAgent() {
            return agent;
        }

        public void setAgent(AgentBean agent) {
            this.agent = agent;
        }

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class AgentBean {
            /**
             * face : http://114.215.83.139/chushi/uploads/agent/liushuai.png
             * id : 1
             * mobile : 13838383838
             * name : 刘帅
             * store : 1
             * store_mobile : 13833338888
             */

            private String face;
            private String id;
            private String mobile;
            private String name;
            private String store;
            private String store_mobile;

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStore() {
                return store;
            }

            public void setStore(String store) {
                this.store = store;
            }

            public String getStore_mobile() {
                return store_mobile;
            }

            public void setStore_mobile(String store_mobile) {
                this.store_mobile = store_mobile;
            }
        }

        public static class OrderBean {
            /**
             * a_id : 1
             * address : aaa
             * addtime : 2017-09-20 16:39
             * fahuo_time : 1970-01-01 08:00
             * fukuan_time : 1970-01-01 08:00
             * id : 362
             * is_pinglun : 0
             * number : MD150589678276129079
             * order_num : 1
             * peisong_star : 0
             * pinglun_content :
             * price : 108.00
             * queren_time : 1970-01-01 08:00
             * shouhuo_mobile : 13513673246
             * shouhuo_name : gzp
             * status : 4
             * yun_price : 8.00
             */

            private String a_id;
            private String address;
            private String addtime;
            private String fahuo_time;
            private String fukuan_time;
            private String id;
            private String is_pinglun;
            private String number;
            private int order_num;
            private String peisong_star;
            private String pinglun_content;
            private String price;
            private String queren_time;
            private String shouhuo_mobile;
            private String shouhuo_name;
            private String status;
            private String yun_price;
            private String gift;

            public String getGift() {
                return gift;
            }

            public void setGift(String gift) {
                this.gift = gift;
            }

            public String getA_id() {
                return a_id;
            }

            public void setA_id(String a_id) {
                this.a_id = a_id;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getFahuo_time() {
                return fahuo_time;
            }

            public void setFahuo_time(String fahuo_time) {
                this.fahuo_time = fahuo_time;
            }

            public String getFukuan_time() {
                return fukuan_time;
            }

            public void setFukuan_time(String fukuan_time) {
                this.fukuan_time = fukuan_time;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIs_pinglun() {
                return is_pinglun;
            }

            public void setIs_pinglun(String is_pinglun) {
                this.is_pinglun = is_pinglun;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public int getOrder_num() {
                return order_num;
            }

            public void setOrder_num(int order_num) {
                this.order_num = order_num;
            }

            public String getPeisong_star() {
                return peisong_star;
            }

            public void setPeisong_star(String peisong_star) {
                this.peisong_star = peisong_star;
            }

            public String getPinglun_content() {
                return pinglun_content;
            }

            public void setPinglun_content(String pinglun_content) {
                this.pinglun_content = pinglun_content;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getQueren_time() {
                return queren_time;
            }

            public void setQueren_time(String queren_time) {
                this.queren_time = queren_time;
            }

            public String getShouhuo_mobile() {
                return shouhuo_mobile;
            }

            public void setShouhuo_mobile(String shouhuo_mobile) {
                this.shouhuo_mobile = shouhuo_mobile;
            }

            public String getShouhuo_name() {
                return shouhuo_name;
            }

            public void setShouhuo_name(String shouhuo_name) {
                this.shouhuo_name = shouhuo_name;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getYun_price() {
                return yun_price;
            }

            public void setYun_price(String yun_price) {
                this.yun_price = yun_price;
            }
        }

        public static class InfoBean {
            /**
             * content :
             * is_pinglun : 0
             * pic : http://114.215.83.139/chushi/uploads/product/tehui/tupian2.png
             * product_num : 10
             * product_price : 100.00
             * star : 0
             * title : 新疆香蕉
             * unit : 500g
             */

            private String content;
            private String is_pinglun;
            private String pic;
            private String product_num;
            private String product_price;
            private String star;
            private String title;
            private String unit;
            private String peisong_name;
            private String product_id;

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }
            public String getPeisong_name() {
                return peisong_name;
            }

            public void setPeisong_name(String peisong_name) {
                this.peisong_name = peisong_name;
            }
            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getIs_pinglun() {
                return is_pinglun;
            }

            public void setIs_pinglun(String is_pinglun) {
                this.is_pinglun = is_pinglun;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
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

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }
        }
    }
}
