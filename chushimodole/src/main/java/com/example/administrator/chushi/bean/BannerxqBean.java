package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/13.
 */

public class BannerxqBean {

    /**
     * code : 200
     * datas : {"id":"3","list":[{"pic":"114.215.83.139/chushi/uploads/product/putong/shangp1.png","product_id":"0"},{"pic":"114.215.83.139/website/chushi/uploads/ad/20171016/59e429549591f.jpg","product_id":"3"}],"name":"满100元","pic":"114.215.83.139/chushi/uploads/activity/u180.jpg"}
     * msg : 成功
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
         * id : 3
         * list : [{"pic":"114.215.83.139/chushi/uploads/product/putong/shangp1.png","product_id":"0"},{"pic":"114.215.83.139/website/chushi/uploads/ad/20171016/59e429549591f.jpg","product_id":"3"}]
         * name : 满100元
         * pic : 114.215.83.139/chushi/uploads/activity/u180.jpg
         */

        private String id;
        private String name;
        private String pic;
        private List<ListBean> list;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * pic : 114.215.83.139/chushi/uploads/product/putong/shangp1.png
             * product_id : 0
             */

            private String pic;
            private String product_id;

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }
        }
    }
}
