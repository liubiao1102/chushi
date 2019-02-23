package com.example.administrator.chushi.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.administrator.chushi.bean.GreenAllBean;

import com.example.administrator.chushi.greendao.gen.GreenAllBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig greenAllBeanDaoConfig;

    private final GreenAllBeanDao greenAllBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        greenAllBeanDaoConfig = daoConfigMap.get(GreenAllBeanDao.class).clone();
        greenAllBeanDaoConfig.initIdentityScope(type);

        greenAllBeanDao = new GreenAllBeanDao(greenAllBeanDaoConfig, this);

        registerDao(GreenAllBean.class, greenAllBeanDao);
    }
    
    public void clear() {
        greenAllBeanDaoConfig.clearIdentityScope();
    }

    public GreenAllBeanDao getGreenAllBeanDao() {
        return greenAllBeanDao;
    }

}