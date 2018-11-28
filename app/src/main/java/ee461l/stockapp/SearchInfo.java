package ee461l.stockapp;

public class SearchInfo {

    private Company company;
    private Quote quote;
    private News[] news;

//    private String searchTerm;
//
//    public SearchInfo(String searchTerm){
//        this.searchTerm = searchTerm;
//    }
//
//    public String getSearchTerm() {
//        return searchTerm;
//    }
//
//    public void setSearchTerm(String searchTerm) {
//        this.searchTerm = searchTerm;
//    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public News[] getNews() {
        return news;
    }

    public void setNews(News[] news) {
        this.news = news;
    }
}
