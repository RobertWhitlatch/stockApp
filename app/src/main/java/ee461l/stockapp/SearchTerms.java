package ee461l.stockapp;

public class SearchTerms {

    private SymbolRefData[] refData;

    public boolean isValidSearch(String search){
        for(SymbolRefData symbol : refData){
            if(symbol.getSymbol().equalsIgnoreCase(search)){
                return (true);
            }
        }
        return(false);
    }

    public SymbolRefData[] getRefData() {
        return refData;
    }

    public void setRefData(SymbolRefData[] refData) {
        this.refData = refData;
    }
}
