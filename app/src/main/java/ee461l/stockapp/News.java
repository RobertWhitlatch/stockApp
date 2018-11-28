package ee461l.stockapp;

public class News {

    private String datetime;
    private String headline;
    private String source;
    private String url;
    private String summary;
    private String related;
    private String image;


//    private String jsonStr;
//
//    public News(String jsonStr){
//        this.jsonStr = jsonStr;
//    }
//
//    public String getJsonStr() {
//        return jsonStr;
//    }
//
//    public void setJsonStr(String jsonStr) {
//        this.jsonStr = jsonStr;
//    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRelated() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
