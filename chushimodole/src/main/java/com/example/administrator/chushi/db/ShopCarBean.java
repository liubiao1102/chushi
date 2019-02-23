package com.example.administrator.chushi.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/9/20.
 */

public class ShopCarBean extends DataSupport{
    private String goodId;
    private String name;
    private String price;
    private String imgUrl;
    private int count;
    private int maxCount;
    private boolean isChecked;

    public ShopCarBean(String goodId, String name, String price, String imgUrl, int count, int maxCount) {
        this.goodId = goodId;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.count = count;
        this.maxCount = maxCount;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
