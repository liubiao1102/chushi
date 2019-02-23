package com.example.administrator.chushi.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/9/14.
 */
@Entity
public class GreenAllBean {
    private String goodId;
    private String name;
    private String price;
    private String imgUrl;
    private int count;
    private int maxCount;
    private boolean isChecked;

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public GreenAllBean(String id, String name, String price, String imgUrl, int count, int maxCount) {
        this.goodId = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.count = count;
        this.maxCount = maxCount;
    }

    @Generated(hash = 357308513)
    public GreenAllBean(String goodId, String name, String price, String imgUrl, int count,
            int maxCount, boolean isChecked) {
        this.goodId = goodId;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.count = count;
        this.maxCount = maxCount;
        this.isChecked = isChecked;
    }

    @Generated(hash = 2104172088)
    public GreenAllBean() {
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsChecked() {
        return this.isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
