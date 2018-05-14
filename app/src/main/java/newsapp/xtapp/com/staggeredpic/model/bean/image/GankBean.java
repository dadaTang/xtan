package newsapp.xtapp.com.staggeredpic.model.bean.image;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * error : false
 * results : [{"_id":"599a482a421aa901c85e5fa1",
 * "createdAt":"2017-08-21T10:40:42.831Z",
 * "desc":"ARKit 实现的超级玛丽，好逼真啊。",
 * "images":["http://img.gank.io/68a0ef31-a2ad-4364-bbd4-f8d1f15b59fe"],
 * "publishedAt":"2017-08-21T11:38:57.363Z",
 * "source":"chrome",
 * "type":"iOS",
 * "url":"https://github.com/bjarnel/arkit-smb-homage",
 * "used":true,
 * "who":"S"},
 */

public class GankBean {

    private boolean error;
    private List<ResultsBean> results;
    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        for (ResultsBean bean : results) {
            if (bean.getImages() == null) {
                if (bean.getType().equals("福利")) {
                    bean.setItemType(ResultsBean.MEIZI);
                } else {
                    bean.setItemType(ResultsBean.TEXT);
                }
            } else {
                bean.setItemType(ResultsBean.IMG);
            }
        }
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements MultiItemEntity {
        /**
         * _id : 599a482a421aa901c85e5fa1
         * createdAt : 2017-08-21T10:40:42.831Z
         * desc : ARKit 实现的超级玛丽，好逼真啊。
         * images : ["http://img.gank.io/68a0ef31-a2ad-4364-bbd4-f8d1f15b59fe"]
         * publishedAt : 2017-08-21T11:38:57.363Z
         * source : chrome
         * type : iOS
         * url : https://github.com/bjarnel/arkit-smb-homage
         * used : true
         * who : S
         */

        public static final int TEXT = 1;
        public static final int IMG = 2;
        public static final int MEIZI = 3;

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;
        private int itemType;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        @Override
        public int getItemType() {
            return itemType;
        }
    }

}
