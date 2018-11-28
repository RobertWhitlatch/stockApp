package ee461l.stockapp;

public class Quote {

    private String symbol;
    private String companyName;
    private String primaryExchange;
    private String sector;
    private String calculationPrice;
    private String open;
    private String openTime;
    private String close;
    private String closeTime;
    private String high;
    private String low;
    private String latestPrice;
    private String latestSource;
    private String latestTime;
    private String latestUpdate;
    private String latestVolume;
    private String iexRealtimePrice;
    private String iexRealtimeSize;
    private String iexLastUpdated;
    private String delayedPrice;
    private String delayedPriceTime;
    private String extendedPrice;
    private String extendedChange;
    private String extendedChangePercent;
    private String extendedPriceTime;
    private String previousClose;
    private String change;
    private String changePercent;
    private String iexMarketPercent;
    private String iexVolume;
    private String avgTotalVolume;
    private String iexBidPrice;
    private String iexBidSize;
    private String iexAskPrice;
    private String iexAskSize;
    private String marketCap;
    private String peRatio;
    private String week52High;
    private String week52Low;
    private String ytdChange;


//    private String jsonStr;
//
//    public Quote(String jsonStr){
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPrimaryExchange() {
        return primaryExchange;
    }

    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCalculationPrice() {
        return calculationPrice;
    }

    public void setCalculationPrice(String calculationPrice) {
        this.calculationPrice = calculationPrice;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(String latestPrice) {
        this.latestPrice = latestPrice;
    }

    public String getLatestSource() {
        return latestSource;
    }

    public void setLatestSource(String latestSource) {
        this.latestSource = latestSource;
    }

    public String getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    public String getLatestUpdate() {
        return latestUpdate;
    }

    public void setLatestUpdate(String latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    public String getLatestVolume() {
        return latestVolume;
    }

    public void setLatestVolume(String latestVolume) {
        this.latestVolume = latestVolume;
    }

    public String getIexRealtimePrice() {
        return iexRealtimePrice;
    }

    public void setIexRealtimePrice(String iexRealtimePrice) {
        this.iexRealtimePrice = iexRealtimePrice;
    }

    public String getIexRealtimeSize() {
        return iexRealtimeSize;
    }

    public void setIexRealtimeSize(String iexRealtimeSize) {
        this.iexRealtimeSize = iexRealtimeSize;
    }

    public String getIexLastUpdated() {
        return iexLastUpdated;
    }

    public void setIexLastUpdated(String iexLastUpdated) {
        this.iexLastUpdated = iexLastUpdated;
    }

    public String getDelayedPrice() {
        return delayedPrice;
    }

    public void setDelayedPrice(String delayedPrice) {
        this.delayedPrice = delayedPrice;
    }

    public String getDelayedPriceTime() {
        return delayedPriceTime;
    }

    public void setDelayedPriceTime(String delayedPriceTime) {
        this.delayedPriceTime = delayedPriceTime;
    }

    public String getExtendedPrice() {
        return extendedPrice;
    }

    public void setExtendedPrice(String extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    public String getExtendedChange() {
        return extendedChange;
    }

    public void setExtendedChange(String extendedChange) {
        this.extendedChange = extendedChange;
    }

    public String getExtendedChangePercent() {
        return extendedChangePercent;
    }

    public void setExtendedChangePercent(String extendedChangePercent) {
        this.extendedChangePercent = extendedChangePercent;
    }

    public String getExtendedPriceTime() {
        return extendedPriceTime;
    }

    public void setExtendedPriceTime(String extendedPriceTime) {
        this.extendedPriceTime = extendedPriceTime;
    }

    public String getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(String previousClose) {
        this.previousClose = previousClose;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(String changePercent) {
        this.changePercent = changePercent;
    }

    public String getIexMarketPercent() {
        return iexMarketPercent;
    }

    public void setIexMarketPercent(String iexMarketPercent) {
        this.iexMarketPercent = iexMarketPercent;
    }

    public String getIexVolume() {
        return iexVolume;
    }

    public void setIexVolume(String iexVolume) {
        this.iexVolume = iexVolume;
    }

    public String getAvgTotalVolume() {
        return avgTotalVolume;
    }

    public void setAvgTotalVolume(String avgTotalVolume) {
        this.avgTotalVolume = avgTotalVolume;
    }

    public String getIexBidPrice() {
        return iexBidPrice;
    }

    public void setIexBidPrice(String iexBidPrice) {
        this.iexBidPrice = iexBidPrice;
    }

    public String getIexBidSize() {
        return iexBidSize;
    }

    public void setIexBidSize(String iexBidSize) {
        this.iexBidSize = iexBidSize;
    }

    public String getIexAskPrice() {
        return iexAskPrice;
    }

    public void setIexAskPrice(String iexAskPrice) {
        this.iexAskPrice = iexAskPrice;
    }

    public String getIexAskSize() {
        return iexAskSize;
    }

    public void setIexAskSize(String iexAskSize) {
        this.iexAskSize = iexAskSize;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public String getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(String peRatio) {
        this.peRatio = peRatio;
    }

    public String getWeek52High() {
        return week52High;
    }

    public void setWeek52High(String week52High) {
        this.week52High = week52High;
    }

    public String getWeek52Low() {
        return week52Low;
    }

    public void setWeek52Low(String week52Low) {
        this.week52Low = week52Low;
    }

    public String getYtdChange() {
        return ytdChange;
    }

    public void setYtdChange(String ytdChange) {
        this.ytdChange = ytdChange;
    }
}
