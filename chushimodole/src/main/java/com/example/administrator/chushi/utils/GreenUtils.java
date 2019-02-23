package com.example.administrator.chushi.utils;

import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.administrator.chushi.MyApplication;
import com.example.administrator.chushi.bean.GreenAllBean;
import com.example.administrator.chushi.greendao.gen.DaoMaster;
import com.example.administrator.chushi.greendao.gen.DaoSession;
import com.example.administrator.chushi.greendao.gen.GreenAllBeanDao;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19.
 */

public class GreenUtils {

    public static DaoSession getDaoSession() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApplication.getInstance(), "chushi_db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }

    public static GreenAllBeanDao getShopCarDao() {
        return getDaoSession().getGreenAllBeanDao();
    }

    public static void insert(GreenAllBean bean) {
        if (isInserted(bean)) {//查重
            return;
        }
        long insert = getShopCarDao().insert(bean);
        if (insert > 0) {
            Toast.makeText(MyApplication.getInstance(), "添加购物车成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MyApplication.getInstance(), "添加购物车失败", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteList(List<GreenAllBean> list) {
        getShopCarDao().deleteInTx(list);//不好使，会崩
    }

    public static void deleteOne(String id) {
        getShopCarDao().queryBuilder().where(GreenAllBeanDao.Properties.GoodId.eq(id))
                .buildDelete().executeDeleteWithoutDetachingEntities();
    }

    public static void deleteAll() {
        getShopCarDao().deleteAll(); // 删除所有
        //        getShopCarDao().getDatabase().execSQL();
    }

    public static List<GreenAllBean> getAllData() {
        List<GreenAllBean> list = getShopCarDao().queryBuilder().list();
        return list;
    }

    public static void updateData(String id, int count) {
        GreenAllBean unique = getShopCarDao().queryBuilder().where(GreenAllBeanDao.Properties.GoodId.eq(id)).build().unique();
        unique.setCount(count);
        getShopCarDao().update(unique);
    }

    public static void updateData(GreenAllBean bean) {
        getShopCarDao().update(bean);
    }

    //查重
    public static boolean isInserted(GreenAllBean bean) {
        List<GreenAllBean> allData = getAllData();
        for (int i = 0; i < allData.size(); i++) {
            if (allData.get(i).getGoodId().equals(bean.getGoodId())) {
                return true;
            }
        }
        return false;
    }

}
