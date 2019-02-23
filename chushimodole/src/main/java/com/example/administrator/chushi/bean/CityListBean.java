package com.example.administrator.chushi.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */

public class CityListBean implements Serializable {
    private String name;
    private List<String> area;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getArea() {
        return area;
    }

    public void setArea(List<String> area) {
        this.area = area;
    }

}
