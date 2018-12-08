package ee461l.stockapp;

final class Define {
    final static String apiEndpoint = "https://api.iextrading.com/1.0";
    final static String symbolRequest = "/ref-data/symbols";
    final static String stockRequest = "/stock/";
    final static String requestCQNSCLP = "/batch?types=company,quote,news,stats,chart,logo,price";
    final static String requestCrypto = "/stock/market/crypto";
}
