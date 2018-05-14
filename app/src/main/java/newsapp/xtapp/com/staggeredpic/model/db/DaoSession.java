package newsapp.xtapp.com.staggeredpic.model.db;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

import newsapp.xtapp.com.staggeredpic.model.bean.image.LikeBean;


public class DaoSession extends AbstractDaoSession {

    private final DaoConfig mDaoConfig;
    private final  LikeBeanDao mLikeBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        mDaoConfig = daoConfigMap.get(LikeBeanDao.class).clone();
        mDaoConfig.initIdentityScope(type);

        mLikeBeanDao = new LikeBeanDao(mDaoConfig, this);

        registerDao(LikeBean.class, mLikeBeanDao);
    }

    public void clear() {
        mDaoConfig.clearIdentityScope();
    }

    public LikeBeanDao getLikeBeanDao() {
        return mLikeBeanDao;
    }

}
