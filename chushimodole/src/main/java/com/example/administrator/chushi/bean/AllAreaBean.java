package com.example.administrator.chushi.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */

public class AllAreaBean implements Serializable {
    private List<ProvinceListBean> province;

    public List<ProvinceListBean> getProvince() {
        return province;
    }

    public void setProvince(List<ProvinceListBean> province) {
        this.province = province;
    }


}
