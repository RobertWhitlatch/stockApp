package ee461l.stockapp;

public class SearchInfo {

    private Company company;
    private Quote quote;
    private News[] news;
    private Stats stats;
    private ChartDay[] chart;
    private Logo logo;
    private /*double*/ String price;

    public GraphData fetchGraphData(){
        GraphData data = new GraphData(chart.length);
        for(int i = chart.length - 1; i >= 0; i--){
            data.addDataPoint(chart[i].getFormattedDate(), chart[i].getCloseDouble(), i);
        }
        return(data);
    }

    public String[] getDisplaySet(){
        String[] set = new String[16 + news.length];
        int i = 0;
        set[i++] = logo.getUrl();
        set[i++] = "Price: " + price;
        set[i++] = "Description: " + company.getDescription();
        set[i++] = "Primary Exchange: " + quote.getPrimaryExchange();
        set[i++] = "Open: " + chart[chart.length-1].getOpen();
        set[i++] = "Close: " + chart[chart.length-1].getClose();
        set[i++] = "High: " + chart[chart.length-1].getHigh();
        set[i++] = "Low: " + chart[chart.length-1].getLow();
        set[i++] = "Change: " + chart[chart.length-1].getChange();
        set[i++] = "Volume: " + chart[chart.length-1].getVolume();
        set[i++] = "Market Cap: " + stats.getMarketcap();
        set[i++] = "30 Day Change Percent: " + stats.getDay30ChangePercent();
        set[i++] = "Debt: " + stats.getDebt();
        set[i++] = "Revenue: " + stats.getRevenue();
        set[i++] = "Gross Profit: " + stats.getGrossProfit();
        set[i++] = "Cash: " + stats.getCash();
        for(News paper : news){
            set[i++] = "Headline: " + paper.getHeadline();
        }
        return set;
    }

    public String getSymbol(){
        return (this.company.getSymbol());
    }

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

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
