package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */

public class GuidePageBean {

    /**
     * code : 200
     * datas : [{"img":"http://114.215.83.139/chushi/uploads/startpage/20171106/5a0008f887b3f.jpg"},{"img":"http://114.215.83.139/chushi/uploads/startpage/20171106/5a0008fecdb1c.jpg"},{"img":""},{"img":""},{"img":""},{"img":""}]
     * msg : 成功
     */

    private int code;
    private String msg;
    private List<DatasBean> datas;

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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * img : http://114.215.83.139/chushi/uploads/startpage/20171106/5a0008f887b3f.jpg
         */

        private String img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
