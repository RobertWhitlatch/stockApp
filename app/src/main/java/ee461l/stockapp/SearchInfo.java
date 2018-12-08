package ee461l.stockapp;

public class SearchInfo {

    private Company company;
    private Quote quote;
    private News[] news;
    private Stats stats;
    private ChartDay[] chart;
    private Logo logo;
    private /*double*/ String price;


    public double[] getWeekofData(){
        double[] week = new double[10];
        //int index = 0;
        for(int i = 0; i< 10; i++){
            week[i] = Double.parseDouble(chart[chart.length-1-i].getClose());
        }
        return week;
    }
    public String[] getWeekofDates(){
        String[] week = new String[10];
        //int index = 0;
        for(int i = 0; i< 10; i++){
            week[i] = chart[chart.length-1-i].getDate();
        }
        return week;
    }
    public String[] getDisplaySet(){
        String[] set = new String[30];
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
            if(i == news.length){
                break;
            }
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
