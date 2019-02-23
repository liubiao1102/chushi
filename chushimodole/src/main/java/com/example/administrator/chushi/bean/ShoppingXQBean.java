package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */

public class ShoppingXQBean {


    /**
     * code : 200
     * data : {"data":{"address":"苏州","banner":[{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fad3d03243e.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fad47b63cc1.jpg","width":649},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fad4849f65b.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fad4acee5e4.jpg","width":649},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fad4b2e8b9f.jpg","width":600}],"baozhiqi":"20","content":"阳澄湖","detail":[{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fae4660145c.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fae46c8652f.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fae47494509.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59faf2d4cdee9.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fbe7562e2c6.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fc4c72682c0.png","width":600}],"guige":"盒","id":"21","is_collect":"0","kou_price":"88.00","kucun":"502","number":"201711021618261888","sales":"0","title":"蟹黄上","tuijian":"2","unit":"公斤","yuan_price":"108.00","zhekou":"1"},"guess":[{"id":"1","kou_price":"0.00","kucun":"0","number":"XP123456","pic":"http://114.215.83.139/chushi/uploads/product/putong/shangp1.png","title":"新疆香蕉113","unit":"500g","yuan_price":"12.00","zhekou":"0"},{"id":"21","kou_price":"88.00","kucun":"502","number":"201711021618261888","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fad3d03243e.jpg","title":"蟹黄上","unit":"公斤","yuan_price":"108.00","zhekou":"1"},{"id":"37","kou_price":"0.00","kucun":"99","number":"201711051744103419","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171105/59fedd4422f54.jpg","title":"肉","unit":"g","yuan_price":"88.00","zhekou":"0"},{"id":"40","kou_price":"0.00","kucun":"93","number":"201711051807006447","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171105/59fee29b9daff.jpg","title":"CE1105","unit":"g","yuan_price":"159.00","zhekou":"0"}],"manjian":"","manzeng":""}
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
         * data : {"address":"苏州","banner":[{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fad3d03243e.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fad47b63cc1.jpg","width":649},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fad4849f65b.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fad4acee5e4.jpg","width":649},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fad4b2e8b9f.jpg","width":600}],"baozhiqi":"20","content":"阳澄湖","detail":[{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fae4660145c.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fae46c8652f.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fae47494509.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59faf2d4cdee9.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fbe7562e2c6.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fc4c72682c0.png","width":600}],"guige":"盒","id":"21","is_collect":"0","kou_price":"88.00","kucun":"502","number":"201711021618261888","sales":"0","title":"蟹黄上","tuijian":"2","unit":"公斤","yuan_price":"108.00","zhekou":"1"}
         * guess : [{"id":"1","kou_price":"0.00","kucun":"0","number":"XP123456","pic":"http://114.215.83.139/chushi/uploads/product/putong/shangp1.png","title":"新疆香蕉113","unit":"500g","yuan_price":"12.00","zhekou":"0"},{"id":"21","kou_price":"88.00","kucun":"502","number":"201711021618261888","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fad3d03243e.jpg","title":"蟹黄上","unit":"公斤","yuan_price":"108.00","zhekou":"1"},{"id":"37","kou_price":"0.00","kucun":"99","number":"201711051744103419","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171105/59fedd4422f54.jpg","title":"肉","unit":"g","yuan_price":"88.00","zhekou":"0"},{"id":"40","kou_price":"0.00","kucun":"93","number":"201711051807006447","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171105/59fee29b9daff.jpg","title":"CE1105","unit":"g","yuan_price":"159.00","zhekou":"0"}]
         * manjian :
         * manzeng :
         */

        private DataBean data;
        private String manjian;
        private String manzeng;
        private List<GuessBean> guess;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getManjian() {
            return manjian;
        }

        public void setManjian(String manjian) {
            this.manjian = manjian;
        }

        public String getManzeng() {
            return manzeng;
        }

        public void setManzeng(String manzeng) {
            this.manzeng = manzeng;
        }

        public List<GuessBean> getGuess() {
            return guess;
        }

        public void setGuess(List<GuessBean> guess) {
            this.guess = guess;
        }

        public static class DataBean {
            /**
             * address : 苏州
             * banner : [{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fad3d03243e.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fad47b63cc1.jpg","width":649},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fad4849f65b.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fad4acee5e4.jpg","width":649},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fad4b2e8b9f.jpg","width":600}]
             * baozhiqi : 20
             * content : 阳澄湖
             * detail : [{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fae4660145c.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fae46c8652f.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59fae47494509.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171102/59faf2d4cdee9.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fbe7562e2c6.jpg","width":600},{"height":395,"pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fc4c72682c0.png","width":600}]
             * guige : 盒
             * id : 21
             * is_collect : 0
             * kou_price : 88.00
             * kucun : 502
             * number : 201711021618261888
             * sales : 0
             * title : 蟹黄上
             * tuijian : 2
             * unit : 公斤
             * yuan_price : 108.00
             * zhekou : 1
             */

            private String address;
            private String baozhiqi;
            private String content;
            private String guige;
            private String id;
            private String is_collect;
            private String kou_price;
            private String kucun;
            private String number;
            private String sales;
            private String title;
            private String tuijian;
            private String unit;
            private String yuan_price;
            private String zhekou;
            private List<BannerBean> banner;
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

            public String getGuige() {
                return guige;
            }

            public void setGuige(String guige) {
                this.guige = guige;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIs_collect() {
                return is_collect;
            }

            public void setIs_collect(String is_collect) {
                this.is_collect = is_collect;
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

            public String getSales() {
                return sales;
            }

            public void setSales(String sales) {
                this.sales = sales;
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

            public List<BannerBean> getBanner() {
                return banner;
            }

            public void setBanner(List<BannerBean> banner) {
                this.banner = banner;
            }

            public List<DetailBean> getDetail() {
                return detail;
            }

            public void setDetail(List<DetailBean> detail) {
                this.detail = detail;
            }

            public static class BannerBean {
                /**
                 * height : 395
                 * pic : http://114.215.83.139/chushi/uploads/product/putong/20171102/59fad3d03243e.jpg
                 * width : 600
                 */

                private int height;
                private String pic;
                private int width;

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }
            }

            public static class DetailBean {
                /**
                 * height : 395
                 * pic : http://114.215.83.139/chushi/uploads/product/putong/20171102/59fae4660145c.jpg
                 * width : 600
                 */

                private int height;
                private String pic;
                private int width;

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }
            }
        }

        public static class GuessBean {
            /**
             * id : 1
             * kou_price : 0.00
             * kucun : 0
             * number : XP123456
             * pic : http://114.215.83.139/chushi/uploads/product/putong/shangp1.png
             * title : 新疆香蕉113
             * unit : 500g
             * yuan_price : 12.00
             * zhekou : 0
             */

            private String id;
            private String kou_price;
            private String kucun;
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
