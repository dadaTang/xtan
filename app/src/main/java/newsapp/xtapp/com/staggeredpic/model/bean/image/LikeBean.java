package newsapp.xtapp.com.staggeredpic.model.bean.image;

public class LikeBean {
    private Long id;

    private String guid;

    private String imageUrl;

    private String title;

    private String url;

    private int type;

    private long time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public LikeBean(Long id, String guid, String imageUrl, String title, String url,
                    int type, long time) {
        this.id = id;
        this.guid = guid;
        this.imageUrl = imageUrl;
        this.title = title;
        this.url = url;
        this.type = type;
        this.time = time;
    }
    public LikeBean() {

    }


}
