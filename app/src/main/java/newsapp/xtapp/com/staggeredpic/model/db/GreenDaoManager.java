package newsapp.xtapp.com.staggeredpic.model.db;


import android.content.Context;

import java.util.List;

import newsapp.xtapp.com.staggeredpic.model.bean.image.LikeBean;

/**
 * GreenDao管理中心
 */
public class GreenDaoManager {

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public GreenDaoManager(Context context) {
        //自己需要处理的表
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "like", null);

        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getmDaoSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

    public LikeBeanDao getLikeBeanDao() {
        return getSession().getLikeBeanDao();
    }

    /**
     * 查询
     */
    public List<LikeBean> queryAll() {
        return getLikeBeanDao().queryBuilder().orderDesc(LikeBeanDao.Properties.Time).build().list();

    }

    /**
     * 新增
     */
    public void insert(LikeBean likeBean) {
        getLikeBeanDao().insert(likeBean);

    }

    /**
     * 根据Guid查询*
     */
    public boolean queryByGuid(String guid) {
        LikeBean likeBean = getLikeBeanDao().queryBuilder().where(LikeBeanDao.Properties.Guid.eq(guid)).build().unique();
        if (null == likeBean) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 根据guid删除
     */
    public void deleteByGuid(String guid) {
        LikeBean bean = getLikeBeanDao()
                .queryBuilder()
                .where(LikeBeanDao.Properties.Guid.eq(guid))
                .build()
                .unique();
        if (null != bean) {
            getLikeBeanDao().delete(bean);
        }
    }

    /**
     * 删除
     *
     * @param likeBean
     */
    public void delete(LikeBean likeBean) {
        getLikeBeanDao().delete(likeBean);
    }


}


