package ee461l.stockapp;

public class Company {

    private String symbol;
    private String exchange;
    private String industry;
    private String website;
    private String description;
    private String CEO;
    private String issueType;
    private String sector;
    private String[] tags;

//    private String jsonStr;
//
//    public Company(String jsonStr){
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCEO() {
        return CEO;
    }

    public void setCEO(String CEO) {
        this.CEO = CEO;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

}
