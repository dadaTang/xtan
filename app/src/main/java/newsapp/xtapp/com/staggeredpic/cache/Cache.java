package newsapp.xtapp.com.staggeredpic.cache;


import java.util.List;

import newsapp.xtapp.com.staggeredpic.model.bean.douban.movie.child.SubjectsBean;
import newsapp.xtapp.com.staggeredpic.util.SPUtil;

/**
 * Created by Horrarndoo on 2017/10/17.
 * <p>
 */

public class Cache {
    /**
     * 获取豆瓣电影hot cache
     *
     * @return 豆瓣电影hot cache
     */
    public static List<SubjectsBean> getHotMovieCache() {
        return SPUtil.getDataList("hot_movie_cache", SubjectsBean.class);
    }

    /**
     * 保存豆瓣电影hot cache
     */
    public static void saveHotMovieCache(List<SubjectsBean> list) {
        SPUtil.setDataList("hot_movie_cache", list);
    }
}
