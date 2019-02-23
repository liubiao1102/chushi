package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */

public class TestBean {

    /**
     * code : 200
     * data : {"data":{"address":"新疆","baozhiqi":"5","content":"种类众多的水果篮，营养丰富","detail":[{"pic":"http://114.215.83.139/chushi/uploads/product/putong/xiangqing.png"},{"pic":"http://114.215.83.139/chushi/uploads/product/putong/ertong2.png"}],"id":"1","kou_price":"0.00","kucun":"100","number":"XP123456","pic1":"http://114.215.83.139/chushi/uploads/product/putong/shangp1.png","pic2":"http://114.215.83.139/chushi/uploads/product/putong/shangp3.png","pic3":"","pic4":"","pic5":"","title":"新疆香蕉","tuijian":"1","unit":"500g","yuan_price":"12.00","zhekou":"0"},"guess":[{"id":"1","kou_price":"0.00","number":"XP123456","pic":"http://114.215.83.139/chushi/uploads/product/putong/shangp1.png","title":"新疆香蕉","unit":"500g","yuan_price":"12.00","zhekou":"0"}]}
     * msg : 成功
     */

    private int code;
    private DataBeanX data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBeanX {
        /**
         * data : {"address":"新疆","baozhiqi":"5","content":"种类众多的水果篮，营养丰富","detail":[{"pic":"http://114.215.83.139/chushi/uploads/product/putong/xiangqing.png"},{"pic":"http://114.215.83.139/chushi/uploads/product/putong/ertong2.png"}],"id":"1","kou_price":"0.00","kucun":"100","number":"XP123456","pic1":"http://114.215.83.139/chushi/uploads/product/putong/shangp1.png","pic2":"http://114.215.83.139/chushi/uploads/product/putong/shangp3.png","pic3":"","pic4":"","pic5":"","title":"新疆香蕉","tuijian":"1","unit":"500g","yuan_price":"12.00","zhekou":"0"}
         * guess : [{"id":"1","kou_price":"0.00","number":"XP123456","pic":"http://114.215.83.139/chushi/uploads/product/putong/shangp1.png","title":"新疆香蕉","unit":"500g","yuan_price":"12.00","zhekou":"0"}]
         */

        private DataBean data;
        private List<GuessBean> guess;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public List<GuessBean> getGuess() {
            return guess;
        }

        public void setGuess(List<GuessBean> guess) {
            this.guess = guess;
        }

        public static class DataBean {
            /**
             * address : 新疆
             * baozhiqi : 5
             * content : 种类众多的水果篮，营养丰富
             * detail : [{"pic":"http://114.215.83.139/chushi/uploads/product/putong/xiangqing.png"},{"pic":"http://114.215.83.139/chushi/uploads/product/putong/ertong2.png"}]
             * id : 1
             * kou_price : 0.00
             * kucun : 100
             * number : XP123456
             * pic1 : http://114.215.83.139/chushi/uploads/product/putong/shangp1.png
             * pic2 : http://114.215.83.139/chushi/uploads/product/putong/shangp3.png
             * pic3 :
             * pic4 :
             * pic5 :
             * title : 新疆香蕉
             * tuijian : 1
             * unit : 500g
             * yuan_price : 12.00
             * zhekou : 0
             */

            private String address;
            private String baozhiqi;
            private String content;
            private String id;
            private String kou_price;
            private String kucun;
            private String number;
            private String pic1;
            private String pic2;
            private String pic3;
            private String pic4;
            private String pic5;
            private String title;
            private String tuijian;
            private String unit;
            private String yuan_price;
            private String zhekou;
            private List<DetailBean> detail;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getBaozhiqi() {
                return baozhiqi;
            }

            public void setBaozhiqi(String baozhiqi) {
                this.baozhiqi = baozhiqi;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getKou_price() {
                return kou_price;
            }

            public void setKou_price(String kou_price) {
                this.kou_price = kou_price;
            }

            public String getKucun() {
                return kucun;
            }

            public void setKucun(String kucun) {
                this.kucun = kucun;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getPic1() {
                return pic1;
            }

            public void setPic1(String pic1) {
                this.pic1 = pic1;
            }

            public String getPic2() {
                return pic2;
            }

            public void setPic2(String pic2) {
                this.pic2 = pic2;
            }

            public String getPic3() {
                return pic3;
            }

            public void setPic3(String pic3) {
                this.pic3 = pic3;
            }

            public String getPic4() {
                return pic4;
            }

            public void setPic4(String pic4) {
                this.pic4 = pic4;
            }

            public String getPic5() {
                return pic5;
            }

            public void setPic5(String pic5) {
                this.pic5 = pic5;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTuijian() {
                return tuijian;
            }

            public void setTuijian(String tuijian) {
                this.tuijian = tuijian;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getYuan_price() {
                return yuan_price;
            }

            public void setYuan_price(String yuan_price) {
                this.yuan_price = yuan_price;
            }

            public String getZhekou() {
                return zhekou;
            }

            public void setZhekou(String zhekou) {
                this.zhekou = zhekou;
            }

            public List<DetailBean> getDetail() {
                return detail;
            }

            public void setDetail(List<DetailBean> detail) {
                this.detail = detail;
            }

            public static class DetailBean {
                /**
                 * pic : http://114.215.83.139/chushi/uploads/product/putong/xiangqing.png
                 */

                private String pic;

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }
            }
        }

        public static class GuessBean {
            /**
             * id : 1
             * kou_price : 0.00
             * number : XP123456
             * pic : http://114.215.83.139/chushi/uploads/product/putong/shangp1.png
             * title : 新疆香蕉
             * unit : 500g
             * yuan_price : 12.00
             * zhekou : 0
             */

            private String id;
            private String kou_price;
            private String number;
            private String pic;
            private String title;
            private String unit;
            private String yuan_price;
            private String zhekou;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getKou_price() {
                return kou_price;
            }

            public void setKou_price(String kou_price) {
                this.kou_price = kou_price;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
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

            public String getYuan_price() {
                return yuan_price;
            }

            public void setYuan_price(String yuan_price) {
                this.yuan_price = yuan_price;
            }

            public String getZhekou() {
                return zhekou;
            }

            public void setZhekou(String zhekou) {
                this.zhekou = zhekou;
            }
        }
    }
}
