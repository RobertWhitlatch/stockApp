package ee461l.stockapp;

public class SymbolRefData {

    private String  symbol;
    private String  name;
    private String  date;
    private boolean isEnabled;
    private String  type;
    private int     iexId;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIexId() {
        return iexId;
    }

    public void setIexId(int iexId) {
        this.iexId = iexId;
    }
}
