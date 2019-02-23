package com.example.administrator.chushi.bean;

/**
 * Created by Administrator on 2017/11/9.
 */

public class LauncherBean {

    /**
     * code : 200
     * datas : {"img1":"http://114.215.83.139/chushi/uploads/startpage/20171108/5a0280e7c9b82.png"}
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
         * img1 : http://114.215.83.139/chushi/uploads/startpage/20171108/5a0280e7c9b82.png
         */

        private String img1;

        public String getImg1() {
            return img1;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }
    }
}
