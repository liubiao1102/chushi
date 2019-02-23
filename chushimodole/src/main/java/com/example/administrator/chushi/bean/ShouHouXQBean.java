package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8.
 */

public class ShouHouXQBean {

    /**
     * code : 200
     * datas : {"data":{"agree_reason":"","id":"2126","number":"151001929466225782","price":"100000000.00","refuse_reason":"","tui_price":"","tui_status":"1","tui_time":"2017-11-08 09:58:19","tui_type":"2","tui_yun_price":"","tuikuanyuanyin":"发货地址填错了"},"info":[{"num":"1","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fc903ae321d.jpg","price":"12.00","title":"测试23"}]}
     * msg : success
     */

    private int code;
    private DatasBean datas;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DatasBean {
        /**
         * data : {"agree_reason":"","id":"2126","number":"151001929466225782","price":"100000000.00","refuse_reason":"","tui_price":"","tui_status":"1","tui_time":"2017-11-08 09:58:19","tui_type":"2","tui_yun_price":"","tuikuanyuanyin":"发货地址填错了"}
         * info : [{"num":"1","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fc903ae321d.jpg","price":"12.00","title":"测试23"}]
         */

        private DataBean data;
        private List<InfoBean> info;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class DataBean {
            /**
             * agree_reason :
             * id : 2126
             * number : 151001929466225782
             * price : 100000000.00
             * refuse_reason :
             * tui_price :
             * tui_status : 1
             * tui_time : 2017-11-08 09:58:19
             * tui_type : 2
             * tui_yun_price :
             * tuikuanyuanyin : 发货地址填错了
             */

            private String agree_reason;
            private String id;
            private String number;
            private String price;
            private String refuse_reason;
            private String tui_price;
            private String tui_status;
            private String tui_time;
            private String tui_type;
            private String tui_yun_price;
            private String tuikuanyuanyin;

            public String getAgree_reason() {
                return agree_reason;
            }

            public void setAgree_reason(String agree_reason) {
                this.agree_reason = agree_reason;
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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getRefuse_reason() {
                return refuse_reason;
            }

            public void setRefuse_reason(String refuse_reason) {
                this.refuse_reason = refuse_reason;
            }

            public String getTui_price() {
                return tui_price;
            }

            public void setTui_price(String tui_price) {
                this.tui_price = tui_price;
            }

            public String getTui_status() {
                return tui_status;
            }

            public void setTui_status(String tui_status) {
                this.tui_status = tui_status;
            }

            public String getTui_time() {
                return tui_time;
            }

            public void setTui_time(String tui_time) {
                this.tui_time = tui_time;
            }

            public String getTui_type() {
                return tui_type;
            }

            public void setTui_type(String tui_type) {
                this.tui_type = tui_type;
            }

            public String getTui_yun_price() {
                return tui_yun_price;
            }

            public void setTui_yun_price(String tui_yun_price) {
                this.tui_yun_price = tui_yun_price;
            }

            public String getTuikuanyuanyin() {
                return tuikuanyuanyin;
            }

            public void setTuikuanyuanyin(String tuikuanyuanyin) {
                this.tuikuanyuanyin = tuikuanyuanyin;
            }
        }

        public static class InfoBean {
            /**
             * num : 1
             * pic : http://114.215.83.139/chushi/uploads/product/putong/20171103/59fc903ae321d.jpg
             * price : 12.00
             * title : 测试23
             */

            private String num;
            private String pic;
            private String price;
            private String title;

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
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
