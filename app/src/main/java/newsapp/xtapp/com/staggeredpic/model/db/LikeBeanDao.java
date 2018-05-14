package newsapp.xtapp.com.staggeredpic.model.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

import newsapp.xtapp.com.staggeredpic.model.bean.image.LikeBean;

public class LikeBeanDao extends AbstractDao<LikeBean,Long> {
    public static final String TABLENAME = "LIKE_BEAN";


    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Guid = new Property(1, String.class, "guid", false, "GUID");
        public final static Property ImageUrl = new Property(2, String.class, "imageUrl", false, "IMAGE_URL");
        public final static Property Title = new Property(3, String.class, "title", false, "TITLE");
        public final static Property Url = new Property(4, String.class, "url", false, "URL");
        public final static Property Type = new Property(5, int.class, "type", false, "TYPE");
        public final static Property Time = new Property(6, long.class, "time", false, "TIME");
    }

    public LikeBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public LikeBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database db, boolean ifNotExits) {
        String constraint = ifNotExits ? "IF NOT EXITS" : "";
      db.execSQL("CREATE TABLE " + constraint + "\"LIKE_BEAN\" (" +       //
                "\"_id\" INTEGER PRIMARY KEY ," +                         // 0: id
                "\"GUID\" TEXT," +                                        // 1: guid
                "\"IMAGE_URL\" TEXT," +                                   // 2: imageUrl
                "\"TITLE\" TEXT," +                                       // 3: title
                "\"URL\" TEXT," +                                         // 4: url
                "\"TYPE\" INTEGER NOT NULL ," +                           // 5: type
                "\"TIME\" INTEGER NOT NULL );");                          // 6: time

    }

    /**
     * 删除表
     *
     * */
    public  static void  dropTable(Database database,boolean ifExists){
        String sql=" DROP TABLE "+(ifExists ?"IF EXISTS ": " ")+
                "\"LIKE_BEAN\"";
        database.execSQL(sql);
    }
    protected final void bindValues(DatabaseStatement stmt, LikeBean entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String guid = entity.getGuid();
        if (guid != null) {
            stmt.bindString(2, guid);
        }

        String imageUrl = entity.getImageUrl();
        if (imageUrl != null) {
            stmt.bindString(3, imageUrl);
        }

        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }

        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(5, url);
        }
        stmt.bindLong(6, entity.getType());
        stmt.bindLong(7, entity.getTime());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LikeBean entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String guid = entity.getGuid();
        if (guid != null) {
            stmt.bindString(2, guid);
        }

        String imageUrl = entity.getImageUrl();
        if (imageUrl != null) {
            stmt.bindString(3, imageUrl);
        }

        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }

        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(5, url);
        }
        stmt.bindLong(6, entity.getType());
        stmt.bindLong(7, entity.getTime());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    public LikeBean readEntity(Cursor cursor, int offset) {
        LikeBean entity = new LikeBean( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // guid
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // imageUrl
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // title
                cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // url
                cursor.getInt(offset + 5), // type
                cursor.getLong(offset + 6) // time
        );
        return entity;
    }

    @Override
    public void readEntity(Cursor cursor, LikeBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setGuid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setImageUrl(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setUrl(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setType(cursor.getInt(offset + 5));
        entity.setTime(cursor.getLong(offset + 6));
    }

    @Override
    protected final Long updateKeyAfterInsert(LikeBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    public Long getKey(LikeBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LikeBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }


}
