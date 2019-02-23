package com.example.administrator.chushi.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.administrator.chushi.bean.GreenAllBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GREEN_ALL_BEAN".
*/
public class GreenAllBeanDao extends AbstractDao<GreenAllBean, Void> {

    public static final String TABLENAME = "GREEN_ALL_BEAN";

    /**
     * Properties of entity GreenAllBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property GoodId = new Property(0, String.class, "goodId", false, "GOOD_ID");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Price = new Property(2, String.class, "price", false, "PRICE");
        public final static Property ImgUrl = new Property(3, String.class, "imgUrl", false, "IMG_URL");
        public final static Property Count = new Property(4, int.class, "count", false, "COUNT");
        public final static Property MaxCount = new Property(5, int.class, "maxCount", false, "MAX_COUNT");
        public final static Property IsChecked = new Property(6, boolean.class, "isChecked", false, "IS_CHECKED");
    }


    public GreenAllBeanDao(DaoConfig config) {
        super(config);
    }
    
    public GreenAllBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GREEN_ALL_BEAN\" (" + //
                "\"GOOD_ID\" TEXT," + // 0: goodId
                "\"NAME\" TEXT," + // 1: name
                "\"PRICE\" TEXT," + // 2: price
                "\"IMG_URL\" TEXT," + // 3: imgUrl
                "\"COUNT\" INTEGER NOT NULL ," + // 4: count
                "\"MAX_COUNT\" INTEGER NOT NULL ," + // 5: maxCount
                "\"IS_CHECKED\" INTEGER NOT NULL );"); // 6: isChecked
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GREEN_ALL_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, GreenAllBean entity) {
        stmt.clearBindings();
 
        String goodId = entity.getGoodId();
        if (goodId != null) {
            stmt.bindString(1, goodId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String price = entity.getPrice();
        if (price != null) {
            stmt.bindString(3, price);
        }
 
        String imgUrl = entity.getImgUrl();
        if (imgUrl != null) {
            stmt.bindString(4, imgUrl);
        }
        stmt.bindLong(5, entity.getCount());
        stmt.bindLong(6, entity.getMaxCount());
        stmt.bindLong(7, entity.getIsChecked() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, GreenAllBean entity) {
        stmt.clearBindings();
 
        String goodId = entity.getGoodId();
        if (goodId != null) {
            stmt.bindString(1, goodId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String price = entity.getPrice();
        if (price != null) {
            stmt.bindString(3, price);
        }
 
        String imgUrl = entity.getImgUrl();
        if (imgUrl != null) {
            stmt.bindString(4, imgUrl);
        }
        stmt.bindLong(5, entity.getCount());
        stmt.bindLong(6, entity.getMaxCount());
        stmt.bindLong(7, entity.getIsChecked() ? 1L: 0L);
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public GreenAllBean readEntity(Cursor cursor, int offset) {
        GreenAllBean entity = new GreenAllBean( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // goodId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // price
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // imgUrl
            cursor.getInt(offset + 4), // count
            cursor.getInt(offset + 5), // maxCount
            cursor.getShort(offset + 6) != 0 // isChecked
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, GreenAllBean entity, int offset) {
        entity.setGoodId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPrice(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setImgUrl(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCount(cursor.getInt(offset + 4));
        entity.setMaxCount(cursor.getInt(offset + 5));
        entity.setIsChecked(cursor.getShort(offset + 6) != 0);
     }
    
    @Override
    protected final Void updateKeyAfterInsert(GreenAllBean entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(GreenAllBean entity) {
        return null;
    }

    @Override
    public boolean hasKey(GreenAllBean entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
