package newsapp.xtapp.com.staggeredpic.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.identityscope.IdentityScopeType;


public class DaoMaster extends AbstractDaoMaster {

    public static final int SCHEMA_VERSION = 1;


    /**
     * Creates underlying database table using DAOs.
     */
    public static void createAllTables(Database database, boolean ifExists) {
        LikeBeanDao.createTable(database, ifExists);
    }

    /**
     * Drops underlying database table using DAOs.
     */
    public static void dropAllTables(Database database, boolean ifExists) {
        LikeBeanDao.dropTable(database, ifExists);
    }


    /**
     * WARNING: Drops all table on Upgrade! Use only during development.
     * Convenience method using a {@link DevOpenHelper}.
     */
    public static DaoSession newDevSession(Context context, String name) {
        Database database = new DevOpenHelper(context, name).getWritableDb();
        DaoMaster daoMaster = new DaoMaster(database);
        return  daoMaster.newSession();
    }

    public DaoMaster(SQLiteDatabase db) {
        this(new StandardDatabase(db));
    }

    public DaoMaster(Database db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(LikeBeanDao.class);
    }

    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }


    /**
     * Calls {@link #createAllTables(Database, boolean)} in {@link #onCreate(Database)} -
     */
    public static abstract class OpenHelper extends DatabaseOpenHelper {

        public OpenHelper(Context context, String name) {
            super(context, name, SCHEMA_VERSION);
        }

        public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(Database db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }

    }


    /**
     * WARNING: Drops all table on Upgrade! Use only during development.
     */
    public static class DevOpenHelper extends OpenHelper {

        public DevOpenHelper(Context context, String name) {
            super(context, name);
        }

        public DevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, name, cursorFactory);
        }


        public void onUpgrade(Database database, int oldVersion, int newVersion) {
            Log.i("greenDao", "Upgrading schema from version " +
                    oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(database, true);
            onCreate(database);
        }


    }




}
