package ee461l.stockapp;

final class Define {
    final static String apiEndpoint = "https://api.iextrading.com/1.0";
    final static String symbolRequest = "/ref-data/symbols";
    final static String stockRequest = "/stock/";
    final static String requestCQNSCLP = "/batch?types=company,quote,news,stats,chart,logo,price";
    final static String requestCrypto = "/stock/market/crypto";
    final static String newsEndpoint = "https://newsapi.org/v2";
    final static String openSearch = "/everything?q=";
    final static String stockMarketNews = "stock+market";
    final static String newsAPIKey = "&apiKey=b2a2dd5161024372ad39e7ab8c1eb9b5";

    final static int LABEL_ANGLE = 90;
}
