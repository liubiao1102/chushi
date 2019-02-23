package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class TuiKuanBean {

    /**
     * code : 200
     * data : [{"yuanyin":"发货地址填错了"},{"yuanyin":"商品数量选错了"},{"yuanyin":"不想买了"}]
     * msg : 成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * yuanyin : 发货地址填错了
         */

        private String yuanyin;

        public String getYuanyin() {
            return yuanyin;
        }

        public void setYuanyin(String yuanyin) {
            this.yuanyin = yuanyin;
        }
    }
}
