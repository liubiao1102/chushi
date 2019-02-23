package com.example.administrator.chushi.bean;

/**
 * Created by Administrator on 2017/8/28.
 */

public class TitleBean {
    private String content;
    private boolean isChecked;

    public TitleBean() {
    }
    public TitleBean(String content, boolean isChecked) {
        this.content = content;
        this.isChecked = isChecked;
    }
    public TitleBean(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
