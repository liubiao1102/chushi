package com.example.administrator.chushi.fragment.mine;

import java.util.List;

/**
 * Created by Administrator on 2017/10/13.
 */
public class HelpBean {

    /**
     * code : 200
     * msg : 成功
     * data : [{"id":"13","name":" 哈哈哈哈帮助测试啊啊啊","detail":"<p>帮助啊帮助啊<\/p>","url":" "},{"id":"14","name":"测试测试啊啊所得到的赌东道赌东道得到的答复是","detail":"<p>帮助啊帮助啊<\/p>","url":" "}]
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
         * id : 13
         * name :  哈哈哈哈帮助测试啊啊啊
         * detail : <p>帮助啊帮助啊</p>
         * url :
         */

        private String id;
        private String name;
        private String detail;
        private String url;

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

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
