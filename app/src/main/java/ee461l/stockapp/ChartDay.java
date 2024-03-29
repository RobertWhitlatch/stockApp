package ee461l.stockapp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChartDay {

    private /*String*/ String date;
    private /*double*/ String open;
    private /*double*/ String high;
    private /*double*/ String low;
    private /*double*/ String close;
    private /*long  */ String volume;
    private /*long  */ String unadjustedVolume;
    private /*double*/ String change;
    private /*double*/ String changePercent;
    private /*double*/ String vwap;
    private /*String*/ String label;
    private /*double*/ String changeOverTime;
    private final transient static DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    public String getDate() {
        return date;
    }

    public Date getFormattedDate(){
        Date dateObj = null;
        try {
            dateObj = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (dateObj);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
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

    public String getClose() {
        return close;
    }

    public double getCloseDouble(){
        return (Double.parseDouble(close));
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getUnadjustedVolume() {
        return unadjustedVolume;
    }

    public void setUnadjustedVolume(String unadjustedVolume) {
        this.unadjustedVolume = unadjustedVolume;
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

    public String getVwap() {
        return vwap;
    }

    public void setVwap(String vwap) {
        this.vwap = vwap;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getChangeOverTime() {
        return changeOverTime;
    }

    public void setChangeOverTime(String changeOverTime) {
        this.changeOverTime = changeOverTime;
    }
}
