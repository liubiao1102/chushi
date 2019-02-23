package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class HomeFragmentBean {


    /**
     * code : 200
     * data : {"activity":[{"id":"7","name":"活动专场测试1","pic":"http://114.215.83.139/chushi/uploads/ad/20171107/5a0165082880c.png"},{"id":"14","name":"测试-6","pic":"http://114.215.83.139/chushi/uploads/ad/20171106/5a001c044d869.jpg"},{"id":"15","name":"测试-6","pic":"http://114.215.83.139/chushi/uploads/ad/20171106/5a001c044d869.jpg"},{"id":"17","name":"如果","pic":"http://114.215.83.139/chushi/uploads/ad/20171123/5a163678a8430.jpg"}],"remai":[{"id":"48","kucun":"135","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171108/5a02a2db56349.png","title":"测试-8","unit":"个","yuan_price":"12.00"},{"id":"49","kucun":"182","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171108/5a02a3a2041fe.png","title":"测试-8-1","unit":"个","yuan_price":"12.00"},{"id":"52","kucun":"118","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171122/5a14f36da0bc1.jpeg","title":"测试商品别动","unit":"456","yuan_price":"98.00"},{"id":"58","kucun":"147","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171128/5a1d116983c56.png","title":"葫芦","unit":"500g","yuan_price":"10.00"},{"id":"46","kucun":"61","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171106/5a0030a736528.png","title":"少时诵诗书","unit":"g","yuan_price":"12.00"}],"thsp":[{"id":"41","kou_price":"0.00","kucun":"162","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171106/59ffe5ea59362.jpg","price":"12.00","title":"测试11-6","unit":"于","yuan_price":"12.00"},{"id":"6","kou_price":"0.00","kucun":"10119","pic":"http://114.215.83.139/chushi/uploads/product/tehui/tupian2.png","price":"12.00","title":"新疆香蕉","unit":"500g","yuan_price":"12.00"},{"id":"19","kou_price":"0.00","kucun":"185","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171027/59f297bb0c0b2.jpeg","price":"100.00","title":"测试特惠商品","unit":"g","yuan_price":"100.00"},{"id":"24","kou_price":"10.00","kucun":"174","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fc199f3025f.png","price":"90.00","title":"大闸蟹","unit":"公斤","yuan_price":"100.00"},{"id":"29","kou_price":"20.00","kucun":"101","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fc5c6766bbd.jpg","price":"80.00","title":"收到","unit":"0","yuan_price":"100.00"},{"id":"32","kou_price":"0.00","kucun":"101","pic":"http://114.215.83.139","price":"12.00","title":"收到","unit":"0","yuan_price":"12.00"},{"id":"51","kou_price":"10.00","kucun":"48","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171122/5a14f0cde7b6a.png","price":"90.00","title":"测试特惠商品别动","unit":"袋","yuan_price":"100.00"},{"id":"40","kou_price":"0.00","kucun":"201","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171105/59fee29b9daff.jpg","price":"159.00","title":"CE1105","unit":"g","yuan_price":"159.00"}],"thsp_name":"特惠商品测试","type":[{"id":"1","name":"水果","pic":"http://114.215.83.139/chushi/uploads/type/1.png"},{"id":"26","name":"测试分类","pic":"http://114.215.83.139/chushi/uploads/type/20171031/59f7e77c2438f.png"},{"id":"30","name":"搜索","pic":"http://114.215.83.139/chushi/uploads/type/20171103/59fc5b6e9d8d8.jpg"},{"id":"33","name":"蔬菜","pic":"http://114.215.83.139/chushi/uploads/type/20171128/5a1cecaa7d55e.png"},{"id":"2","name":"肉类","pic":"http://114.215.83.139/chushi/uploads/type/2.png"},{"id":"3","name":"干货","pic":"http://114.215.83.139/chushi/uploads/type/3.png"},{"id":"4","name":"调味品","pic":"http://114.215.83.139/chushi/uploads/type/4.png"},{"id":"5","name":"半成品","pic":"http://114.215.83.139/chushi/uploads/type/5.png"}],"yytc":[{"id":"28","kucun":"374","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fc578561d5d.jpg","title":"测试","unit":"于","yuan_price":"0.00"},{"id":"1","kucun":"224","pic":"http://114.215.83.139/chushi/uploads/product/putong/shangp1.png","title":"新疆香蕉113","unit":"500g","yuan_price":"12.00"},{"id":"2","kucun":"9995","pic":"http://114.215.83.139/chushi/uploads/product/yingyang/shangp2.png","title":"1新疆香蕉","unit":"500g","yuan_price":"12.00"}],"yytc_name":"营养套餐"}
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
         * activity : [{"id":"7","name":"活动专场测试1","pic":"http://114.215.83.139/chushi/uploads/ad/20171107/5a0165082880c.png"},{"id":"14","name":"测试-6","pic":"http://114.215.83.139/chushi/uploads/ad/20171106/5a001c044d869.jpg"},{"id":"15","name":"测试-6","pic":"http://114.215.83.139/chushi/uploads/ad/20171106/5a001c044d869.jpg"},{"id":"17","name":"如果","pic":"http://114.215.83.139/chushi/uploads/ad/20171123/5a163678a8430.jpg"}]
         * remai : [{"id":"48","kucun":"135","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171108/5a02a2db56349.png","title":"测试-8","unit":"个","yuan_price":"12.00"},{"id":"49","kucun":"182","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171108/5a02a3a2041fe.png","title":"测试-8-1","unit":"个","yuan_price":"12.00"},{"id":"52","kucun":"118","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171122/5a14f36da0bc1.jpeg","title":"测试商品别动","unit":"456","yuan_price":"98.00"},{"id":"58","kucun":"147","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171128/5a1d116983c56.png","title":"葫芦","unit":"500g","yuan_price":"10.00"},{"id":"46","kucun":"61","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171106/5a0030a736528.png","title":"少时诵诗书","unit":"g","yuan_price":"12.00"}]
         * thsp : [{"id":"41","kou_price":"0.00","kucun":"162","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171106/59ffe5ea59362.jpg","price":"12.00","title":"测试11-6","unit":"于","yuan_price":"12.00"},{"id":"6","kou_price":"0.00","kucun":"10119","pic":"http://114.215.83.139/chushi/uploads/product/tehui/tupian2.png","price":"12.00","title":"新疆香蕉","unit":"500g","yuan_price":"12.00"},{"id":"19","kou_price":"0.00","kucun":"185","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171027/59f297bb0c0b2.jpeg","price":"100.00","title":"测试特惠商品","unit":"g","yuan_price":"100.00"},{"id":"24","kou_price":"10.00","kucun":"174","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fc199f3025f.png","price":"90.00","title":"大闸蟹","unit":"公斤","yuan_price":"100.00"},{"id":"29","kou_price":"20.00","kucun":"101","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fc5c6766bbd.jpg","price":"80.00","title":"收到","unit":"0","yuan_price":"100.00"},{"id":"32","kou_price":"0.00","kucun":"101","pic":"http://114.215.83.139","price":"12.00","title":"收到","unit":"0","yuan_price":"12.00"},{"id":"51","kou_price":"10.00","kucun":"48","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171122/5a14f0cde7b6a.png","price":"90.00","title":"测试特惠商品别动","unit":"袋","yuan_price":"100.00"},{"id":"40","kou_price":"0.00","kucun":"201","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171105/59fee29b9daff.jpg","price":"159.00","title":"CE1105","unit":"g","yuan_price":"159.00"}]
         * thsp_name : 特惠商品测试
         * type : [{"id":"1","name":"水果","pic":"http://114.215.83.139/chushi/uploads/type/1.png"},{"id":"26","name":"测试分类","pic":"http://114.215.83.139/chushi/uploads/type/20171031/59f7e77c2438f.png"},{"id":"30","name":"搜索","pic":"http://114.215.83.139/chushi/uploads/type/20171103/59fc5b6e9d8d8.jpg"},{"id":"33","name":"蔬菜","pic":"http://114.215.83.139/chushi/uploads/type/20171128/5a1cecaa7d55e.png"},{"id":"2","name":"肉类","pic":"http://114.215.83.139/chushi/uploads/type/2.png"},{"id":"3","name":"干货","pic":"http://114.215.83.139/chushi/uploads/type/3.png"},{"id":"4","name":"调味品","pic":"http://114.215.83.139/chushi/uploads/type/4.png"},{"id":"5","name":"半成品","pic":"http://114.215.83.139/chushi/uploads/type/5.png"}]
         * yytc : [{"id":"28","kucun":"374","pic":"http://114.215.83.139/chushi/uploads/product/putong/20171103/59fc578561d5d.jpg","title":"测试","unit":"于","yuan_price":"0.00"},{"id":"1","kucun":"224","pic":"http://114.215.83.139/chushi/uploads/product/putong/shangp1.png","title":"新疆香蕉113","unit":"500g","yuan_price":"12.00"},{"id":"2","kucun":"9995","pic":"http://114.215.83.139/chushi/uploads/product/yingyang/shangp2.png","title":"1新疆香蕉","unit":"500g","yuan_price":"12.00"}]
         * yytc_name : 营养套餐
         */

        private String thsp_name;
        private String yytc_name;
        private List<ActivityBean> activity;
        private List<RemaiBean> remai;
        private List<ThspBean> thsp;
        private List<TypeBean> type;
        private List<YytcBean> yytc;

        public String getThsp_name() {
            return thsp_name;
        }

        public void setThsp_name(String thsp_name) {
            this.thsp_name = thsp_name;
        }

        public String getYytc_name() {
            return yytc_name;
        }

        public void setYytc_name(String yytc_name) {
            this.yytc_name = yytc_name;
        }

        public List<ActivityBean> getActivity() {
            return activity;
        }

        public void setActivity(List<ActivityBean> activity) {
            this.activity = activity;
        }

        public List<RemaiBean> getRemai() {
            return remai;
        }

        public void setRemai(List<RemaiBean> remai) {
            this.remai = remai;
        }

        public List<ThspBean> getThsp() {
            return thsp;
        }

        public void setThsp(List<ThspBean> thsp) {
            this.thsp = thsp;
        }

        public List<TypeBean> getType() {
            return type;
        }

        public void setType(List<TypeBean> type) {
            this.type = type;
        }

        public List<YytcBean> getYytc() {
            return yytc;
        }

        public void setYytc(List<YytcBean> yytc) {
            this.yytc = yytc;
        }

        public static class ActivityBean {
            /**
             * id : 7
             * name : 活动专场测试1
             * pic : http://114.215.83.139/chushi/uploads/ad/20171107/5a0165082880c.png
             */

            private String id;
            private String name;
            private String pic;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class RemaiBean {
            /**
             * id : 48
             * kucun : 135
             * pic : http://114.215.83.139/chushi/uploads/product/putong/20171108/5a02a2db56349.png
             * title : 测试-8
             * unit : 个
             * yuan_price : 12.00
             */

            private String id;
            private String kucun;
            private String pic;
            private String title;
            private String unit;
            private String yuan_price;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getKucun() {
                return kucun;
            }

            public void setKucun(String kucun) {
                this.kucun = kucun;
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
        }

        public static class ThspBean {
            /**
             * id : 41
             * kou_price : 0.00
             * kucun : 162
             * pic : http://114.215.83.139/chushi/uploads/product/putong/20171106/59ffe5ea59362.jpg
             * price : 12.00
             * title : 测试11-6
             * unit : 于
             * yuan_price : 12.00
             */

            private String id;
            private String kou_price;
            private String kucun;
            private String pic;
            private String price;
            private String title;
            private String unit;
            private String yuan_price;

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
        }

        public static class TypeBean {
            /**
             * id : 1
             * name : 水果
             * pic : http://114.215.83.139/chushi/uploads/type/1.png
             */

            private String id;
            private String name;
            private String pic;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class YytcBean {
            /**
             * id : 28
             * kucun : 374
             * pic : http://114.215.83.139/chushi/uploads/product/putong/20171103/59fc578561d5d.jpg
             * title : 测试
             * unit : 于
             * yuan_price : 0.00
             */

            private String id;
            private String kucun;
            private String pic;
            private String title;
            private String unit;
            private String yuan_price;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getKucun() {
                return kucun;
            }

            public void setKucun(String kucun) {
                this.kucun = kucun;
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
        }
    }
}
