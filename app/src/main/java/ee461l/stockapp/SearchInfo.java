package ee461l.stockapp;

public class SearchInfo {

    private Company company;
    private Quote quote;
    private News[] news;
    private Stats stats;
    private ChartDay[] chart;

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

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public ChartDay[] getChart() {
        return chart;
    }

    public void setChart(ChartDay[] chart) {
        this.chart = chart;
    }
}
